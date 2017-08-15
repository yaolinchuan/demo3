package com.example.demo.oauth2server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "name", callSuper = false)
@ToString(exclude = "name", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roleinfos")
public class RoleEntity extends AbstractPersistable<Long> {

    @NotNull
    @Column(name = "role_name", nullable = false, unique = true, length = 100)
    private String name;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean disabled;

    @Singular
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "roles_authorities",
            joinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authorityId", referencedColumnName = "id")})
    private Set<AuthorityEntity> authorityEntities;


//    @JsonIgnore
//    @Singular
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Set<UserEntity> users;

//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<RoleAuthorityXrefEntity> authorities;
//
//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private Set<UserRoleXrefEntity> users;

}
