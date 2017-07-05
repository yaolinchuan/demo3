package filter.error

import com.netflix.zuul.ZuulFilter

/**
 * Created by liyuhong on 2017/7/4.
 */
class TestErrorFilter extends ZuulFilter {
    @Override
    String filterType() {
        return "error"
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
        return null
    }
}
