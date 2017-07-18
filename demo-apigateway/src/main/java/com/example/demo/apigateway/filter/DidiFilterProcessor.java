//package com.example.demo.apigateway.filter;
//
//import com.netflix.zuul.FilterProcessor;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//
///**
// * Created by liyuhong on 2017/7/7.
// */
//public class DidiFilterProcessor  extends FilterProcessor {
//    @Override
//    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
//        try {
//            return super.processZuulFilter(filter);
//        } catch (ZuulException e) {
//            RequestContext ctx = RequestContext.getCurrentContext();
//            ctx.set("failed.exception", e);
//            ctx.set("failed.filter", filter);
//            throw e;
//        }
//    }
//}
