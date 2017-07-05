package com.example.demo.apigateway.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyuhong on 2017/7/4.
 */
@Component
@ConfigurationProperties(prefix="zuul.filter")
public class FilterConfiguration {

    @Getter @Setter
    private String root;
    @Getter @Setter
    private Integer interval;


}
