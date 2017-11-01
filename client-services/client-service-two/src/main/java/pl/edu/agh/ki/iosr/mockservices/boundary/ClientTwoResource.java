package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.ki.iosr.mockservices.control.ExecutionService;

import java.util.Random;

@RestController
public class ClientTwoResource {

    @Autowired
    ExecutionService executionService;

    @RequestMapping(path = "generate-tasks", method = RequestMethod.GET)
    public String generateTasks() {
        int N = new Random().nextInt(100);
        executionService.executeTasks(N);
        return "Executes " + N + " tasks...";
    }
}
