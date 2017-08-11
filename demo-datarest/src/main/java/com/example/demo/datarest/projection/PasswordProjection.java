package com.example.demo.datarest.projection;

import com.example.demo.datarest.entity.Person;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by liyuhong on 2017/8/11.
 */
@Projection(name = "pwd", types = {Person.class})
public interface PasswordProjection {
    String getPwd();
}
