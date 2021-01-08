package demoProject.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"demoProject"})
public class TestAppInitializer {
    public static void main(String[] args) {
        SpringApplication.run(TestAppInitializer.class,args);
    }
}
