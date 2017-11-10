package pl.edu.agh.ki.iosr.mockservices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan({"pl.edu.agh.ki.iosr.mockservices.boundary", "pl.edu.agh.ki.iosr.mockservices.control"})
public class IntegralServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(IntegralServiceApplication.class, args);
    }
}
