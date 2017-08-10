package com.example.demo.oauth2server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by liyuhong on 2017/8/8.
 */
@Configuration
public class UserIDAuditorBean implements AuditorAware<String> {

    @Autowired
    private AuthenticationTrustResolver authenticationTrustResolver;

    @Override
    public String getCurrentAuditor() {
        String defaultUserID = "SYSTEM";
        SecurityContext ctx = SecurityContextHolder.getContext();
        if (ctx == null) {
            return defaultUserID;
        }
        if (ctx.getAuthentication() == null || authenticationTrustResolver.isAnonymous(ctx.getAuthentication())) {
            return defaultUserID;
        }
        if (ctx.getAuthentication().getPrincipal() == null) {
            return defaultUserID;
        }
        Object principal = ctx.getAuthentication().getPrincipal();
        if (principal instanceof String) {
            return (String) principal;
        } else if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return String.valueOf(principal);
        }


    }
}
