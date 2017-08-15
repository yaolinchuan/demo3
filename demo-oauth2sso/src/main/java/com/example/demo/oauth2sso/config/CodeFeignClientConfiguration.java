package com.example.demo.oauth2sso.config;

import feign.RequestInterceptor;
import org.springframework.cloud.security.oauth2.client.feign.OAuth2FeignRequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

/**
 * Created by liyuhong on 2017/8/15.
 */
public class CodeFeignClientConfiguration {
    // @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(OAuth2ClientContext oAuth2ClientContext, OAuth2ProtectedResourceDetails resource) {

        return new OAuth2FeignRequestInterceptor(oAuth2ClientContext, resource);
    }

    OAuth2ClientContext oauth2ClientContext() {
        return new DefaultOAuth2ClientContext();
    }


    OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails clientCredentialsResourceDetails = new AuthorizationCodeResourceDetails();
        clientCredentialsResourceDetails.setClientId("acme");
        clientCredentialsResourceDetails.setClientSecret("acmesecret");
        clientCredentialsResourceDetails.setAuthenticationScheme(AuthenticationScheme.form);
        clientCredentialsResourceDetails.setAccessTokenUri("http://localhost:8010/oauth2server/oauth/token");
        clientCredentialsResourceDetails.setUserAuthorizationUri("http://localhost:8010/oauth2server/oauth/authorize");
        return clientCredentialsResourceDetails;

    }
}
