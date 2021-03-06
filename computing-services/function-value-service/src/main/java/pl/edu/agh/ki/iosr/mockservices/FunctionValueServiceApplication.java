package pl.edu.agh.ki.iosr.mockservices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@EnableDiscoveryClient
@Configuration
@ComponentScan("pl.edu.agh.ki.iosr.mockservices")
public class FunctionValueServiceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(FunctionValueServiceApplication.class, args);
    }

}
