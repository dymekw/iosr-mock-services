package pl.edu.agh.ki.iosr.mockservices.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.edu.agh.ki.iosr.mockservices.FunctionDTO;
import pl.edu.agh.ki.iosr.mockservices.RestTemplateProvider;
import pl.edu.agh.ki.iosr.mockservices.URLProvider;

@Service
@Primary
public class RestFunctionValueClient implements FunctionValueClient {

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

    @Override
    public double getValue(FunctionDTO f, double x) {
        return restTemplate.postForObject(getURL(FUNCTION_VALUE_SERVICE_GET_VALUE_PATH) + "?x=" + x, f, double.class);
    }
}
