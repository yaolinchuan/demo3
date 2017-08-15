package com.example.demo.oauth2sso.service;

import com.example.demo.oauth2sso.config.ClientCredentialsFeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by liyuhong on 2017/8/15.
 */
@FeignClient(value = "apigateway", configuration = ClientCredentialsFeignClientConfiguration.class)
public interface ClientCredentialsHomeService {
    @GetMapping(value = "/oauth2resource/")
    String home();
}