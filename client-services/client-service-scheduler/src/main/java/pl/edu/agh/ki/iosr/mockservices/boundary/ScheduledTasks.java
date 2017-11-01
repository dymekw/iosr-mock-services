package pl.edu.agh.ki.iosr.mockservices.boundary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.RandomFunctionProvider;
import pl.edu.agh.ki.iosr.mockservices.RestTemplateProvider;
import pl.edu.agh.ki.iosr.mockservices.URLProvider;
import java.util.Random;

@Component
public class ScheduledTasks {

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

    @Scheduled(fixedRate =  1000)
    public void createNewTask() {
        FunctionDTO f = RandomFunctionProvider.generateFunction();

        double result;
        if (new Random().nextBoolean()) {
            result = restTemplate.postForObject(getFunctionValueServiceURL(FUNCTION_VALUE_SERVICE_GET_VALUE_PATH) + "?x=" + new Random().nextInt(100), f, double.class);
            System.err.println("VALUE: " + f.toString() + " = " + result);
        } else {
            double a = new Random().nextDouble();
            double b = a + new Random().nextDouble();
            result = restTemplate.postForObject(getIntegralServiceURL(INTEGRAL_SERVICE_INTEGRAL_PATH) + "?a=" + a + "&b=" + b, f, double.class);
            System.err.println("INTEGRAL: " + f.toString() + " = " + result);
        }
    }
}
