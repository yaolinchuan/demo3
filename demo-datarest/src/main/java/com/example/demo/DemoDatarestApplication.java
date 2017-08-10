package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class DemoDatarestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDatarestApplication.class, args);
    }
}
