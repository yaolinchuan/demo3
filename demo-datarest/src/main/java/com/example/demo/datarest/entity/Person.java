package com.example.demo.datarest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by liyuhong on 2017/8/10.
 */
@Data
@EqualsAndHashCode(of = "firstName", callSuper = false)
@ToString(of = "firstName", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @RestResource(exported = false)
    private String pwd;


//    @ManyToMany(mappedBy = "persons", fetch = FetchType.LAZY)
//    @JsonIgnore
//    private Set<Role> roles;

    @Singular
    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonToRoleXre> roles;
}
