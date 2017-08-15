package com.example.demo.oauth2sso.service;

import com.example.demo.oauth2sso.config.FeignClientConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by liyuhong on 2017/8/15.
 */
@FeignClient(value = "apigateway", configuration = ImplicitHomeService.class)
public interface ImplicitHomeService {
    @GetMapping(value = "/oauth2resource/")
    String home();
}
