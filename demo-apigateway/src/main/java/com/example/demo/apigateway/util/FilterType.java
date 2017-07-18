package com.example.demo.apigateway.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liyuhong on 2017/7/7.
 */
public enum FilterType {
    PRE("pre"),
    ROUTE("route"),
    POST("post"),
    ERROR("error");
    @Getter @Setter
    private String value;
    private FilterType(String value){
        this.value=getValue();
    }

}
