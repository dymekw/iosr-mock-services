package pl.edu.agh.ki.iosr.mockservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("pl.edu.agh.ki.iosr.mockservices")
public class ClientServiceOneApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientServiceOneApplication.class, args);
    }
}
