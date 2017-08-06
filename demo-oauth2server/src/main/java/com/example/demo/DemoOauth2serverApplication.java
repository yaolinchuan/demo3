package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication

public class DemoOauth2serverApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOauth2serverApplication.class, args);
    }
}
