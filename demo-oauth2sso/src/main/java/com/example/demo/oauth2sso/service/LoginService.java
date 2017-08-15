package com.example.demo.oauth2sso.service;

import com.example.demo.oauth2sso.config.CodeFeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Created by liyuhong on 2017/8/15.
 */
@FeignClient(value = "apigateway", configuration = CodeFeignClientConfiguration.class)
public interface LoginService {
    @GetMapping(value = "/oauth2server/login")
    String login();

}
