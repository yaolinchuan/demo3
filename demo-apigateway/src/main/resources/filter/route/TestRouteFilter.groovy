package filter.post

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import groovy.util.logging.Slf4j
import javax.servlet.http.HttpServletResponse


/**
 * Created by liyuhong on 2017/7/4.
 */
@Slf4j
class TestRouteFilter extends ZuulFilter {
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
        return false
    }

    @Override
    Object run() {
        log.info("this is a test route filter: receive response")
        HttpServletResponse response = RequestContext.getCurrentContext().getResponse()
        response.getWriter().append("xx")
        response.flushBuffer()
        return null
    }
}
