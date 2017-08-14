package com.example.demo.datarest.repository;

import com.example.demo.datarest.entity.Greeting;
import com.example.demo.datarest.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * Created by liyuhong on 2017/8/14.
 */
@RestResource(exported = false)
public interface ItemRepository extends JpaRepository<Item, Long> {
}
