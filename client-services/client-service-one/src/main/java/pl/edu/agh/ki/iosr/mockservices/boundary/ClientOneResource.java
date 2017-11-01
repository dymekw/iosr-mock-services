package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.RandomFunctionProvider;
import pl.edu.agh.ki.iosr.mockservices.RestTemplateProvider;
import pl.edu.agh.ki.iosr.mockservices.URLProvider;

import java.util.Random;

@RestController
public class ClientOneResource {

    private static final String FUNCTION_VALUE_SERVICE_NAME = "FUNCTION-VALUE-SERVICE";
    private static final String FUNCTION_VALUE_SERVICE_URL_PREFIX = "/rest/functions";
    private static final String FUNCTION_VALUE_SERVICE_GET_VALUE_PATH = "/get-value";

    private static final String INTEGRAL_SERVICE_NAME = "INTEGRAL-SERVICE";
    private static final String INTEGRAL_SERVICE_INTEGRAL_PATH = "/integral";

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    private RestTemplate getRestTemplate() {
        return RestTemplateProvider.restTemplate(FunctionDTO.class, FunctionDTO.class);
    }

    private String getFunctionValueServiceURL(String path) {
        return URLProvider.getURL(discoveryClient,  FUNCTION_VALUE_SERVICE_NAME, FUNCTION_VALUE_SERVICE_URL_PREFIX) + path;
    }

    private String getIntegralServiceURL(String path) {
        return URLProvider.getURL(discoveryClient,  INTEGRAL_SERVICE_NAME, "") + path;
    }

    @RequestMapping(path = "random", method = RequestMethod.GET)
    public String generateRandomComputationTask() {
        FunctionDTO f = RandomFunctionProvider.generateFunction();
        double x = new Random().nextDouble();
        double result = restTemplate.postForObject(getFunctionValueServiceURL(FUNCTION_VALUE_SERVICE_GET_VALUE_PATH) + "?x=" + x, f, double.class);

        return f.toString() + " where x=" + x + " is equal to " + result;
    }

    @RequestMapping(path = "integral", method = RequestMethod.GET)
    public String generateintegralTask() {
        FunctionDTO f = RandomFunctionProvider.generateFunction();
        double a = new Random().nextDouble();
        double b = a + new Random().nextDouble();
        double result = restTemplate.postForObject(getIntegralServiceURL(INTEGRAL_SERVICE_INTEGRAL_PATH) + "?a=" + a + "&b=" + b, f, double.class);

        return f.toString() + " where a=" + a + " and b=" + b + " is equal to " + result;
    }
}
