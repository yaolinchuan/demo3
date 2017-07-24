package com.example.demo.security.resource;

import com.sun.net.httpserver.HttpContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * Created by liyuhong on 2017/7/12.
 */
@RestController
@Api(description="主页")
@Slf4j
public class HomeController {

    @ApiOperation(value="主页",  notes="主页",produces = "application/json")
    @GetMapping(value = "/" )
    @Secured("ROLE_ADMIN")
    public String home() {
        return "hello world";
    }

    @ApiOperation(value="主页",  notes="主页",produces = "application/json")
    @GetMapping(value = "/user" )
    @Secured("ROLE_USER")
    public String user(Principal principal) {
        String username;
        if (principal instanceof UserDetails) {
            username= ((UserDetails)principal).getUsername();
        } else {
             username = principal.getName();
        }
        log.info(principal.toString());
        return username;
    }
    @ApiOperation(value="test",  notes="test",produces = "application/json")
    @PostMapping(value = "/test" )
    public User test(@RequestBody User user) {

        return user;
    }


}
