package com.example.demo.oauth2sso.resource;

import com.example.demo.oauth2sso.config.PasswordFeignClientConfiguration;
import com.example.demo.oauth2sso.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private HomeService homeService;
    @Autowired
    private GatewayHomeService gatewayHomeService;

    @Autowired
    private ClientCredentialsHomeService clientCredentialsHomeService;

    @Autowired
    private PasswordHomeService passwordHomeService;

    @Autowired
    private ImplicitHomeService implicitHomeService;

    @GetMapping(value = "/")
    public String home() {
        return homeService.home();
    }

    @GetMapping(value = "/gateway")
    public String gatewayHome() {
        return gatewayHomeService.home();
    }

    @GetMapping(value = "/client")
    public String clienthome() {
        return clientCredentialsHomeService.home();
    }

    @GetMapping(value = "/password")
    public String passwordhome() {
        return passwordHomeService.home();
    }

    @GetMapping(value = "/implicit")
    public String implicithome() {
        return implicitHomeService.home();
    }
}
