package com.example.demo.apigateway.filter.post

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.http.HttpServletResponseWrapper
import groovy.util.logging.Slf4j
import org.apache.zookeeper.Login
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletResponse

/**
 * Created by liyuhong on 2017/7/4.
 */
@Slf4j
@Component
class TestPostFilter extends ZuulFilter {


    @Override
    String filterType() {
        return "post"
    }

    @Override
    int filterOrder() {
        return 0
    }

    @Override
    boolean shouldFilter() {
        log.info("this is a test post shouldFilter")
        RequestContext context = RequestContext.getCurrentContext()
        if (context.getRequest().getHeader("posttest")!=null){
            return true
        }
        return false
    }

    @Override
    Object run() {
        log.info("this is a test post filter: receive response")
        RequestContext context = RequestContext.getCurrentContext()
        log.info(context.getResponseStatusCode().toString())
        context.setSendZuulResponse(true)
        String result = "";
        if (context.getResponseDataStream() != null) {
            result = context.getResponseDataStream().text
            log.info("获取：{}",result)
        }

        context.setResponseBody(result+"post")


        return null
    }
}
