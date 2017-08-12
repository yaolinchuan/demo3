package com.example.demo.oauth2server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "value", callSuper = false)
@ToString(of = "value", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "scope")
public class ScopeEntity extends AbstractPersistable<Long> {

    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

    @NonNull
    @NotNull
    @Column(name = "auto_approve", nullable = false)
    private Boolean autoApprove;

//    @JsonIgnore
//    @Singular
//    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
//    private Set<ClientDetailsEntity> clientDetailsEntities;

 /*   @OneToMany(mappedBy = "scope", fetch = FetchType.LAZY)
    @Singular
    private Set<ClientDetailsToScopesXrefEntity> clientDetailsToScopesXrefs;*/



}
