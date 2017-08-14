package com.example.demo.datarest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by liyuhong on 2017/8/14.
 */
@Data
@Entity
public class Greeting {
    @Id
    @GeneratedValue
    private Long id;

    private String value;

    @OneToMany(mappedBy = "greeting", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private Set<Item> items = new HashSet<>();

}
