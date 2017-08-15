package com.example.demo.apigateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@ToString(of = "id", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ZuulRouteEntity extends AbstractPersistable<Long> {

    private String zuulrouteid;

    private String fullPath;

    private String path;

    private String location;

    private String prefix;

    private Boolean retryable;

    private boolean customSensitiveHeaders;

    private boolean prefixStripped = true;

    @ElementCollection(targetClass = String.class)
    private Set<String> sensitiveHeaders;

    @JsonIgnore
    @Override
    public Long getId() {
        return super.getId();
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return super.isNew();
    }

//    @Column(name = "is_grpc")
//    private Boolean                         is_grpc;


//    @Column(name = "grpc_group")
//    private String                          group;
//
//    @Column(name = "grpc_version")
//    private String                          version;
//
//    @Column(name = "grpc_method")
//    private String                          method;

//    @OneToMany(mappedBy = "route", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<ZuulGrpcFieldMappingEntity> fieldMapping;

}
