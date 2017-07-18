package com.example.demo.testclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by liyuhong on 2017/6/27.
 */
@FeignClient(value = "testserver",fallback = HomeHystrixService.class)
public interface HomeService {

    @RequestMapping("/")
    public String home();
}
