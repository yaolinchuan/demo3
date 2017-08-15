package com.example.demo.oauth2server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by dewafer on 2016/12/16.
 */
@Data
@EqualsAndHashCode(of = "authorityName", callSuper = false)
@ToString(of = "authorityName", callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "authorities")
public class AuthorityEntity extends AbstractAuditable<Long> {

    @Size(min = 1, max = 50)
    @Column(name = "authority_name", unique = true, length = 50)
    private String authorityName;


    private String uri;
    /*  @Getter @Setter
      private Integer resourceId;*/


    private String method;

    @NonNull
    @NotNull
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "resourceId", nullable = false)
    private ResourceEntity resourceEntity;



    @NotNull
    @Column(name = "disabled", nullable = false)
    @ColumnDefault("False")
    private boolean disabled;


}
