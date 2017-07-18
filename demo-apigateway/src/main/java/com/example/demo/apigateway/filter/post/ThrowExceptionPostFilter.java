package com.example.demo.apigateway.filter.post;

import com.example.demo.apigateway.util.FilterType;
import com.netflix.zuul.ZuulFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Created by liyuhong on 2017/7/7.
 */
@Component
@Slf4j
public class ThrowExceptionPostFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 10;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        new RuntimeException("异常处理测试");
        return null;
    }
}
