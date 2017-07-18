package com.example.demo.apigateway.filter.error

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component

import javax.servlet.http.HttpServletResponse

/**
 * Created by liyuhong on 2017/7/4.
 */
@Component
@Slf4j
class TestErrorFilter extends ZuulFilter {
    @Override
    String filterType() {
        return "error"
    }

    @Override
    int filterOrder() {
        return  10
    }

    @Override
    boolean shouldFilter() {
        log.info("this is a test error shouldFilter")
        return true
    }

    @Override
    Object run() {
        log.info("this is a test error filter: receive response")
        RequestContext ctx = RequestContext.getCurrentContext()
        Throwable throwable =ctx.getThrowable()
        log.error("this error :{}"+throwable.getCause().getMessage())
        ctx.set("error.status_code",HttpServletResponse.SC_INTERNAL_SERVER_ERROR)
        ctx.set("error.exception",throwable.getCause())
        ctx.setResponseBody("error")



        return null
    }
}
