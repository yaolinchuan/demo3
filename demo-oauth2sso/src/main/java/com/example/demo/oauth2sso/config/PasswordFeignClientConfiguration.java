package com.example.demo.oauth2sso.config;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

/**
 * Created by liyuhong on 2017/8/15.
 */
public class PasswordFeignClientConfiguration {

    // @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {

        return new OAuth2FeignRequestInterceptor(oauth2ClientContext(), resource());
    }

    OAuth2ClientContext oauth2ClientContext() {
        return new DefaultOAuth2ClientContext();
    }

    OAuth2ProtectedResourceDetails resource() {
        ResourceOwnerPasswordResourceDetails passwordResourceDetails = new ResourceOwnerPasswordResourceDetails();
        passwordResourceDetails.setUsername("admin");
        passwordResourceDetails.setPassword("admin888");
        // passwordResourceDetails.setAuthenticationScheme(AuthenticationScheme.form);
        passwordResourceDetails.setClientId("acme");
        passwordResourceDetails.setClientSecret("acmesecret");
        passwordResourceDetails.setAccessTokenUri("http://localhost:8010/oauth2server/oauth/token");
        return passwordResourceDetails;

    }
}
