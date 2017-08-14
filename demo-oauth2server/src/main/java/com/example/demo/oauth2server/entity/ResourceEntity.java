package com.example.demo.oauth2server.entity;

import lombok.*;

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
@Table(name = "resources")
public class ResourceEntity extends AbstractAuditable<Long> {

    @NonNull
    @NotNull
    @Column(name = "value", nullable = false)
    private String value;

//    @Singular
//    @OneToMany(mappedBy = "resourceId", fetch = FetchType.LAZY)
//    private Set<ClientDetailsToResourceIdXrefEntity> clientDetailsToResourceIdXrefs;
}
