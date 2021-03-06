package com.example.demo.oauth2sso.service;

import com.example.demo.oauth2sso.config.ClientCredentialsFeignClientConfiguration;
import com.example.demo.oauth2sso.config.CodeFeignClientConfiguration;
import com.example.demo.oauth2sso.config.FeignClientConfiguration;
import com.example.demo.oauth2sso.config.PasswordFeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "oauth2resource", configuration = CodeFeignClientConfiguration.class)
public interface HomeService {
    @GetMapping(value = "/")
    String home();
}
