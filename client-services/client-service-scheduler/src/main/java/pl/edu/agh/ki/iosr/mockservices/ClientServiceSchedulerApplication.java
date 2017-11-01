package pl.edu.agh.ki.iosr.mockservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class ClientServiceSchedulerApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(ClientServiceSchedulerApplication.class);
    }
}
