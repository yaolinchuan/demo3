package com.example.demo.apigateway.filter.route

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

import javax.servlet.http.HttpServletResponse


/**
 * Created by liyuhong on 2017/7/4.
 */
@Slf4j
@Component
class TestRouteFilter extends ZuulFilter {

    @Autowired
    private RestTemplate restTemplate
    @Override
    String filterType() {
        return "route"
    }

    @Override
    int filterOrder() {
        return 0
    }

    @Override
    boolean shouldFilter() {
        log.info("this is a test route shouldFilter")
        RequestContext context = RequestContext.getCurrentContext()

        if (context.getRequest().getHeader("routetest")!=null){
            return true
         }
         return false
    }

    @Override
    Object run() {
        log.info("this is a test route filter: receive response")

        RequestContext context = RequestContext.getCurrentContext()
        context.setSendZuulResponse(true)
        restTemplate =new RestTemplate()
        String s1=restTemplate.getForObject("http://localhost:8010/testserver/test", String.class)
        String s2=restTemplate.getForObject("http://localhost:8010/testserver/", String.class)
        log.info("==============>"+s)
        context.setResponseBody(s1+s2)
        return null
    }
}
