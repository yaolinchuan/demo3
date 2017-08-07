package com.example.demo.oauth2sso.resource;

import com.example.demo.oauth2sso.service.GatewayHomeService;
import com.example.demo.oauth2sso.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private GatewayHomeService gatewayHomeService;

    @GetMapping(value = "/")
    public String home() {
        return homeService.home();
    }

    @GetMapping(value = "/gateway")
    public String gatewayHome() {
        return gatewayHomeService.home();
    }
}
