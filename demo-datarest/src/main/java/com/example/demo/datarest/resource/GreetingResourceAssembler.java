package com.example.demo.datarest.resource;

import com.example.demo.datarest.entity.Greeting;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

/**
 * Created by liyuhong on 2017/8/14.
 */
public class GreetingResourceAssembler extends ResourceAssemblerSupport<Greeting, GreetingResource> {
    public GreetingResourceAssembler() {
        super(GreetingController.class, GreetingResource.class);
    }

    @Override
    public GreetingResource toResource(Greeting greeting) {
        GreetingResource resource = createResourceWithId(greeting.getId(), greeting);
        return resource;
    }

    @Override
    protected GreetingResource instantiateResource(Greeting greeting) {
        return new GreetingResource(greeting);
    }
}
