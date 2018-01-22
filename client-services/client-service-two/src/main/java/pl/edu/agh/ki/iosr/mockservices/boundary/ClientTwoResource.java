package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.ki.iosr.mockservices.control.ActiveMQClient;
import pl.edu.agh.ki.iosr.mockservices.control.ExecutionService;

import java.util.Random;

@RestController
public class ClientTwoResource {

    @Autowired
    ExecutionService executionService;

    @Autowired
    ActiveMQClient activeMQClient;

    @RequestMapping(path = "generate-tasks", method = RequestMethod.GET)
    public String generateTasks() {
        int N = new Random().nextInt(100);
        executionService.executeTasks(N);
        return "Executes " + N + " tasks...";
    }

    @RequestMapping(path = "random-offset", method = RequestMethod.GET)
    public String randomOffest() {
        int N = 100 + new Random().nextInt(100);
        activeMQClient.run(N);
        return "Set offset to " + N;
    }
}
