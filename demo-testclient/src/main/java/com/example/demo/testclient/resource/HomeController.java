package com.example.demo.testclient.resource;

import com.example.demo.testclient.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyuhong on 2017/6/27.
 */
@RestController
@RefreshScope
public class HomeController {
    @Value("${test:XXX}")
    private String test;
    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Autowired
    private HomeService homeService;
    @RequestMapping("/")
    public String home(){
        return homeService.home()+test;
    }
}
