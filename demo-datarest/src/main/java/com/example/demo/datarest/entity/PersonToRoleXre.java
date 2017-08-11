package com.example.demo.datarest.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by liyuhong on 2017/8/11.
 */
@Data
@NoArgsConstructor
@Entity
public class PersonToRoleXre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NonNull
    @NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @NonNull
    @NotNull
    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    //@Where(clause = "disabled = False")
    private Role role;
}
