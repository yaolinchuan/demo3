package com.example.demo.oauth2server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by liyuhong on 2017/8/8.
 */
@Configuration
public class UserIDAuditorBean implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        String UserID = "admin";
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null) {
            return UserID;
        }
        if (ctx.getAuthentication() == null) {
            return UserID;
        }
        if (ctx.getAuthentication().getPrincipal() == null) {
            return UserID;
        }
        Object principal = ctx.getAuthentication().getPrincipal();
        if (principal.getClass().isAssignableFrom(String.class)) {
            return (String) principal;
        } else {
            return UserID;
        }


    }
}
