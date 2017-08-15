package com.example.demo.oauth2server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "clientId", callSuper = false)
@ToString(exclude = "clientSecret", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client_details")
@DynamicUpdate(true)
public class ClientDetailsEntity extends AbstractAuditable<Long> {

    @NonNull
    @NotNull
    @Column(name = "client_id", unique = true, nullable = false, length = 200)
    private String                                            clientId;

    @NonNull
    @NotNull
    @Column(name = "client_secret", nullable = false)
    private String                                            clientSecret;

    @Column(name = "access_token_validity_seconds")
    @ColumnDefault("60")
    private Integer                                           accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity_seconds")
    @ColumnDefault("1440")
    private Integer                                           refreshTokenValiditySeconds;


    @Singular
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "client_details_grant_types",
            joinColumns = {@JoinColumn(name = "clientDetailId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "grantTypeId", referencedColumnName = "id")})
    private Set<GrantTypeEntity> grantTypeEntities;

    @Singular
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "client_details_scopes",
            joinColumns = {@JoinColumn(name = "clientDetailId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "scopeId", referencedColumnName = "id")})
    private Set<ScopeEntity> scopeEntities;


    @Singular
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "client_details_resources",
            joinColumns = {@JoinColumn(name = "clientDetailId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "resourceId", referencedColumnName = "id")})
    private Set<ResourceEntity> resourceEntities;


    @Singular
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(name = "client_details_authorities",
            joinColumns = {@JoinColumn(name = "clientDetailId", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authorityId", referencedColumnName = "id")})
    private Set<AuthorityEntity> authorityEntities;


//    @Singular
//    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//    private Set<ClientDetailsToAuthorizedGrantTypeXrefEntity> authorizedGrantTypeXrefs;
//
//    @Singular
//    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//    private Set<ClientDetailsToScopesXrefEntity>              scopeXrefs;

    //    @Singular
//    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//    private Set<ClientDetailsToResourceIdXrefEntity>          resourceIdXrefs;
//
    @JsonIgnore
    @Singular("redirectUri")
    @OneToMany(mappedBy = "clientDetails", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<RedirectUriEntity>                            redirectUris;
//
//    @OneToOne(mappedBy = "clientDetail", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
//    private ClientDetailsLimitEntity                                 clientLimit;

}
