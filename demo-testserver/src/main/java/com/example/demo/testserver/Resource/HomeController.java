package com.example.demo.testserver.Resource;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;


/**
 * Created by liyuhong on 2017/6/27.
 */
@RestController
@Slf4j
public class HomeController {
    @RequestMapping("/")
    public String home(HttpServletRequest request)  {

        log.info("===<call trace - 2, Traceid={}, Spanid={}>===",
        request.getHeader("X-B3-Traceid"), request.getHeader("X-B3-Spanid"));
        if(new Random().nextBoolean())
        try {


            log.info("home超时");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "home";
    }

    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public void test(@RequestBody String s)  {
        System.out.print(s);

    }
}
