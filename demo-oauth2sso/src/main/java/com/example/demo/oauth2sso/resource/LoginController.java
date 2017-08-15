package com.example.demo.oauth2sso.resource;

import com.example.demo.oauth2sso.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyuhong on 2017/8/15.
 */
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
//    @GetMapping(value = "/login")
//    public String home() {
//        return loginService.login();
//    }
}
