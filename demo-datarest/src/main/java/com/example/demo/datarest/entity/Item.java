package com.example.demo.datarest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by liyuhong on 2017/8/14.
 */
@Data
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Greeting greeting;

}
