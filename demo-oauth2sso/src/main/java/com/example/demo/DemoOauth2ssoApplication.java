package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableOAuth2Sso
@EnableFeignClients
@EnableDiscoveryClient
@EnableCircuitBreaker
public class DemoOauth2ssoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoOauth2ssoApplication.class, args);
    }
}
