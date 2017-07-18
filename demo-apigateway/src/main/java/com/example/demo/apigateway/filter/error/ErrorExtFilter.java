//package com.example.demo.apigateway.filter.error;
//
//
//import com.example.demo.apigateway.util.FilterType;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//
///**
// * Created by liyuhong on 2017/7/7.
// */
//@Component
//@Slf4j
//public class ErrorExtFilter extends ErrorFilter {
//
//    @Override
//    public int filterOrder() {
//        return 30;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        RequestContext cxt = RequestContext.getCurrentContext();
//        ZuulFilter failedFilter =(ZuulFilter) cxt.get("failed.filter");
//        if(failedFilter != null && failedFilter.filterType().equals ("post")) {
//            return true;
//        }
//        return false;
//
//    }
//
//
//
//
//}
