package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;

import java.util.Random;

@RestController
public class FunctionValueServiceResource {

    @RequestMapping(path = "/get-value", method = RequestMethod.POST)
    public double getValue(@RequestBody FunctionDTO function, @RequestParam(value = "x", defaultValue = "0") double x) throws InterruptedException {
        Thread.sleep(new Random().nextInt(1000));
        return function.getValue(x);
    }
}
