package pl.edu.agh.ki.iosr.mockservices.control;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.RandomFunctionProvider;
import pl.edu.agh.ki.iosr.mockservices.RestTemplateProvider;
import pl.edu.agh.ki.iosr.mockservices.URLProvider;

import java.util.Random;

@Service
public class ExecutionService {

    private static final String FUNCTION_VALUE_SERVICE_NAME = "FUNCTION-VALUE-SERVICE";
    private static final String FUNCTION_VALUE_SERVICE_URL_PREFIX = "/rest/functions";
    private static final String FUNCTION_VALUE_SERVICE_GET_VALUE_PATH = "/get-value";

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    RestTemplate restTemplate;

    static Log log = LogFactory.getLog(ExecutionService.class.getName());

    @Bean
    private RestTemplate getRestTemplate() {
        return RestTemplateProvider.restTemplate(FunctionDTO.class, FunctionDTO.class);
    }


    @Async
    public void executeTasks(int N) {
        for (int i=0; i<N; i++) {
            log.info(executeTask());
        }
    }

    private String getURL(String path) {
        return URLProvider.getURL(discoveryClient,  FUNCTION_VALUE_SERVICE_NAME, FUNCTION_VALUE_SERVICE_URL_PREFIX) + path;
    }

    private String executeTask() {
        FunctionDTO f = RandomFunctionProvider.generateFunction();
        double x = new Random().nextDouble();
        double result = restTemplate.postForObject(getURL(FUNCTION_VALUE_SERVICE_GET_VALUE_PATH) + "?x=" + x, f, double.class);

        return f.toString() + " where x=" + x + " is equal to " + result;
    }
}
