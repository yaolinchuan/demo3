package filter.post

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import com.netflix.zuul.http.HttpServletResponseWrapper
import groovy.util.logging.Slf4j

import javax.servlet.http.HttpServletResponse

/**
 * Created by liyuhong on 2017/7/4.
 */
@Slf4j
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
        return false
    }

    @Override
    Object run() {
        log.info("this is a test post filter: receive response")
        RequestContext context = RequestContext.getCurrentContext()
        log.info(context.getResponseDataStream())
        log.info(context.getResponseStatusCode())
        log.info(context.getResponseBody())
        String result = "";
        if (context.getResponseDataStream() != null) {
            result = context.getResponseDataStream().text
        }
        HttpServletResponse response = context.getResponse()
        response.getWriter().append(result).append(" hello")
        response.flushBuffer()


        return null
    }
}
