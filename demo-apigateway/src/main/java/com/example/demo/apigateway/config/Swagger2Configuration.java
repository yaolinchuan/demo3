package com.example.demo.apigateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by liyuhong on 2017/8/8.
 */
@Configuration
@EnableSwagger2
@Import({springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class})
public class Swagger2Configuration {
    @Value("${swagger.basePackage}")
    private String basePackage;
    @Value("${swagger.author}")
    private String author;
    @Value("${swagger.title}")
    private String title;
    @Value("${swagger.description}")
    private String description;
    @Value("${swagger.url}")
    private String url;
    @Value("${swagger.version}")
    private String version;


//    @Value("${security.userOauth.clientId}")
//    private String authClientId;
//
//    @Value("${security.userOauth.clientSecret}")
//    private String authClientSecret;
//
//    @Value("${security.userOauth.type}")
//    private String type;
//
//    @Value("${security.userOauth.authorizationUrl}")
//    private String authorizationUrl;
//
//    @Value("${security.userOauth.tokenUrl}")
//    private String tokenUrl;
//
//    @Value("${security.userOauth.tokenName}")
//    private String tokenName;
//
//    @Value("${security.userOauth.scope.code}")
//    private String scopeCode;
//
//    @Value("${security.userOauth.scope.desc}")
//    private String scopeDesc;
//
//    @Value("${app.key}")
//    private String appKey;
//
//    @Value("${app.name}")
//    private String appName;
//
//    @Value("${app.desc}")
//    private String appDesc;
//
//    @Value("${app.version}")
//    private String appVersion;
//
//    @Value("${app.termsOfServiceUrl}")
//    private String termsOfServiceUrl;
//
//    @Value("${app.contact.name}")
//    private String contactName;
//
//    @Value("${app.contact.url}")
//    private String contactUrl;
//
//    @Value("${app.contact.email}")
//    private String contactEmail;
//
//    @Value("${app.license}")
//    private String license;
//
//    @Value("${app.licenseUrl}")
//    private String licenseUrl;


    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                //  .securitySchemes(new ArrayList(oauth()))
                //   .securityContexts(new ArrayList(securityContext()))
                .apiInfo(apiInfo());
    }

//    @Bean
//    SecurityScheme apiKey() {
//        return new ApiKey(appName, appKey, "header");
//    }
//
//    @Bean
//    SecurityContext securityContext() {
//        AuthorizationScope[] scopes = new AuthorizationScope[]{new AuthorizationScope(scopeCode, scopeDesc)};
//        SecurityReference securityReference = SecurityReference
//                .builder()
//                .reference(type)
//                .scopes(scopes)
//                .build();
//
//
//        return SecurityContext
//                .builder()
//                .securityReferences(new ArrayList(securityReference))
//                .forPaths(ant("/api/**"))
//                .build();
//    }
//
//    @Bean
//    SecurityScheme oauth() {
//        return new OAuthBuilder()
//                .name(type)
//                .grantTypes(grantTypes())
//                .scopes(scopes())
//                .build();
//    }
//
//    List<AuthorizationScope> scopes() {
//        return new ArrayList(new AuthorizationScope(scopeCode, scopeDesc));
//    }
//
//    List<GrantType> grantTypes() {
//        List<GrantType> grants = new ArrayList(new AuthorizationCodeGrant(
//                new TokenRequestEndpoint(authorizationUrl, authClientId, authClientSecret),
//                new TokenEndpoint(tokenUrl, tokenName)));
//        return grants;
//    }
//    @Bean
//    public SecurityConfiguration securityInfo() {
//        return new SecurityConfiguration(authClientId, authClientSecret, scopeCode,
//                appKey, appKey, ApiKeyVehicle.HEADER, "", ",");
//    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .termsOfServiceUrl(url)
                .version(version)
                .build();
    }


}