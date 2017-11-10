package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.control.IntegralComputingService;


@RestController
public class IntegralServiceResource {

    @Autowired
    IntegralComputingService integralComputingService;

    @RequestMapping(path = "/integral", method = RequestMethod.POST)
    public double getIntegral(@RequestBody FunctionDTO function,
                              @RequestParam(value = "a", defaultValue = "0") double a,
                              @RequestParam(value = "b", defaultValue = "1") double b,
                              @RequestParam(value = "e", defaultValue = "0.01") double e) {
        return integralComputingService.computeIntegral(function, a, b, e);
    }
}
