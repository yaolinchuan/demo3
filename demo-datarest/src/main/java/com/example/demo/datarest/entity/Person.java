package com.example.demo.datarest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by liyuhong on 2017/8/10.
 */
@Data
@NoArgsConstructor
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;


    @ManyToMany(mappedBy = "persons", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Role> roles;
}
