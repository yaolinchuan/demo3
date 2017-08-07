package com.example.demo.oauth2sso.service;

import com.example.demo.oauth2sso.config.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "apigateway", configuration = FeignClientConfiguration.class)
public interface GatewayHomeService {
    @GetMapping(value = "/oauth2resource/")
    String home();
}
