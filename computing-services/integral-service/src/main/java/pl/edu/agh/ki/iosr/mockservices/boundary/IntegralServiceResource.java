package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.RestTemplateProvider;
import pl.edu.agh.ki.iosr.mockservices.URLProvider;


@RestController
public class IntegralServiceResource {

    private static final String FUNCTION_VALUE_SERVICE_NAME = "FUNCTION-VALUE-SERVICE";
    private static final String FUNCTION_VALUE_SERVICE_URL_PREFIX = "/rest/functions";
    private static final String FUNCTION_VALUE_SERVICE_GET_VALUE_PATH = "/get-value";

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    private RestTemplate getRestTemplate() {
        return RestTemplateProvider.restTemplate(FunctionDTO.class, FunctionDTO.class);
    }

    private String getURL(String path) {
        return URLProvider.getURL(discoveryClient,  FUNCTION_VALUE_SERVICE_NAME, FUNCTION_VALUE_SERVICE_URL_PREFIX) + path;
    }

    private double getValue(FunctionDTO f, double x) {
        return restTemplate.postForObject(getURL(FUNCTION_VALUE_SERVICE_GET_VALUE_PATH) + "?x=" + x, f, double.class);
    }

    @RequestMapping(path = "/integral", method = RequestMethod.POST)
    public double getIntegral(@RequestBody FunctionDTO function,
                              @RequestParam(value = "a", defaultValue = "0") double a,
                              @RequestParam(value = "b", defaultValue = "1") double b,
                              @RequestParam(value = "e", defaultValue = "0.01") double e) {
        return computeIntegral(function, a, b, e);
    }

    private double computeIntegral(FunctionDTO f, double a, double b, double e) {
        double P1 = (b-a) * (getValue(f, a) + getValue(f, b)) / 2;
        double P2 = (b-a) * (getValue(f, a)/2 + getValue(f, b)/2 + getValue(f, (a+b)/2))/2;

        if (Math.abs(P1 - P2) <= e) {
            return (P1 + P2) / 2;
        }
        return computeIntegral(f, a, (a+b)/2, e) + getIntegral(f, (a+b)/2, b, e);
    }
}
