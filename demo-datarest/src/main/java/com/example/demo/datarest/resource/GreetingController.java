package com.example.demo.datarest.resource;

import com.example.demo.datarest.entity.Greeting;
import com.example.demo.datarest.repository.GreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by liyuhong on 2017/8/14.
 */
@RestController
@RequestMapping("/greetings")
public class GreetingController {
    @Autowired
    private GreetingRepository greetingRepository;

    @RequestMapping("/{id}")
    public GreetingResource greeting(@PathVariable(value = "id") Long id) {
        return new GreetingResourceAssembler().toResource(greetingRepository.findOne(id));
    }


}
