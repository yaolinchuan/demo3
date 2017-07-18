//package com.example.demo.apigateway.filter;
//
//import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
//import org.springframework.web.context.request.RequestAttributes;
//
//import java.util.Map;
//
///**
// * Created by liyuhong on 2017/7/7.
// */
//public class DidiErrorAttributes extends DefaultErrorAttributes {
//
//    @Override
//    public Map<String, Object> getErrorAttributes (
//            RequestAttributes requestAttributes, boolean includeStackTrace){
//        Map<String, Object> result = super.getErrorAttributes(requestAttributes, includeStackTrace);
//        result.remove("exception");
//        return result;
//    }
//}
