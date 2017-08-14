package com.example.demo.datarest.resource;

import com.example.demo.datarest.entity.Greeting;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by liyuhong on 2017/8/14.
 */
public class GreetingResource extends Resource {


    public GreetingResource(Greeting greeting) {
        super(greeting);
    }


}

