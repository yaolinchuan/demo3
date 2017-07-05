package filter.post

import com.netflix.zuul.ZuulFilter
import com.netflix.zuul.context.RequestContext
import groovy.util.logging.Slf4j

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Created by liyuhong on 2017/7/4.
 */
@Slf4j
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
        return false
    }

    @Override
    Object run() {
        log.info("this is a pre post filter: receive response")
        HttpServletRequest request = RequestContext.getCurrentContext().getRequest()
        request.getReader().println()
        return null
    }
}
