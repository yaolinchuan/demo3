package com.example.demo.testclient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liyuhong on 2017/6/27.
 */
@Service
public class HomeHystrixService implements HomeService {


    @Override
    public String home(){
        return "HomeHystrix";
    }
}
