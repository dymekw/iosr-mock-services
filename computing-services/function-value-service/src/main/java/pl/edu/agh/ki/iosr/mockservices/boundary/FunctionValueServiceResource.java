package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.control.FunctionValueComputingService;

@RestController
public class FunctionValueServiceResource {

    @Autowired
    FunctionValueComputingService service;

    @RequestMapping(path = "/get-value", method = RequestMethod.POST)
    public double getValue(@RequestBody FunctionDTO function, @RequestParam(value = "x", defaultValue = "0") double x) {
        return service.getValue(function, x);
    }
}
