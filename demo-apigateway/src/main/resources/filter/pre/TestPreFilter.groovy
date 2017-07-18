package com.example.demo.apigateway.filter.pre

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by liyuhong on 2017/7/4.
 */
@Slf4j
@Component
class TestPreFilter extends ZuulFilter {
    @Override
    String filterType() {
        return "pre"
    }


    @Override
    int filterOrder() {
        return 0
    }

    @Override
    boolean shouldFilter() {
        log.info("this is a test pre shouldFilter")
        RequestContext context = RequestContext.getCurrentContext()
        if (context.getRequest().getHeader("pretest")!=null){
            return true
        }
        return false
    }

    @Override
    Object run() {
        log.info("this is a  test pre  filter: receive response")
        RequestContext context = RequestContext.getCurrentContext()
        HttpServletRequest request = context.getRequest()
        HttpServletResponse response = context.getResponse()
        //是否
        context.setSendZuulResponse(true)
        return null
    }
}
