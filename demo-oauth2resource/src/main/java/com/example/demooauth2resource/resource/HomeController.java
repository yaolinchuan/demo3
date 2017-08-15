package com.example.demooauth2resource.resource;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Secured("ROLE_ADMIN")
    @RequestMapping(value = "/")
    public String home() {
        return "hello world from oauth2resource";

    }
}
