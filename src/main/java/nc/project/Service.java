package nc.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "nc.project")
public class Service {

    public static void main(String[] args) {
        SpringApplication.run(Service.class, args);
    }
}