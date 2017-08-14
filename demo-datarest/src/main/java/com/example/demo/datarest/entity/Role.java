package com.example.demo.datarest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by liyuhong on 2017/8/10.
 */
@Data
@EqualsAndHashCode(of = "username", callSuper = false)
@ToString(of = "name", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
//    @JsonIgnore
//    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JoinTable(name = "roles_users",
//            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")})
//    private Set<Person> persons;

    @Singular
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PersonToRoleXre> persons;

}
