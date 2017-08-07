package com.example.demooauth2resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient
public class DemoOauth2resourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOauth2resourceApplication.class, args);
    }
}
