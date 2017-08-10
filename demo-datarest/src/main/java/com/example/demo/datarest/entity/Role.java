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
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "roles_users",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")})
    private Set<Person> persons;

}
