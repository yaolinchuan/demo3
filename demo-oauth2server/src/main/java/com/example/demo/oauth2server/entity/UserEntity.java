package com.example.demo.oauth2server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "username", callSuper = false)
@ToString(of = "username", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "userinfos")
@DynamicUpdate(true)
public class UserEntity extends AbstractAuditable<Long> {

    public static final String           NAME_REGEX = "^[A-Za-z0-9_]*$";

    @NotNull
    @Pattern(regexp = NAME_REGEX)
    @Size(max = 50)
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String                       username;

    @NotNull
    @RestResource(exported = false)
    //@JsonIgnore
    @Size(max = 60)
    @Column(name = "pwd", length = 60, nullable = false, updatable = false)
    private String                       password;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean                      disabled;


    @Singular
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "id")})
    private Set<RoleEntity> roles;


    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean accountNonExpired;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean accountNonLocked;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean credentialsNonExpired;

/*    @NotNull
    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean enabled =true;*/

//    @Singular
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<UserRoleXrefEntity>      roles;
//
//    @Singular
//    @OneToMany(mappedBy = "authority", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<UserAuthorityXrefEntity> authorities;

}
