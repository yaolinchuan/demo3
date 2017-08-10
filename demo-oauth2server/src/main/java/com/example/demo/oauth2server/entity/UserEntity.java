package com.example.demo.oauth2server.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
public class UserEntity extends AbstractAuditable<Long> {

    public static final String NAME_REGEX = "^[A-Za-z0-9_]*$";

    @NotNull
    @Pattern(regexp = NAME_REGEX)
    @Size(max = 50)
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false)
    @ColumnDefault("False")
    private boolean disabled;

    @Singular
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRoleXrefEntity> roles;

    @Singular
    @OneToMany(mappedBy = "authority", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserAuthorityXrefEntity> authorities;

}
