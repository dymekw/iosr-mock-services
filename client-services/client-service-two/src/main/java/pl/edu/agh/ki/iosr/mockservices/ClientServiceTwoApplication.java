package pl.edu.agh.ki.iosr.mockservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
@Configuration
@ComponentScan("pl.edu.agh.ki.iosr.mockservices")
public class ClientServiceTwoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientServiceTwoApplication.class, args);
    }

}
