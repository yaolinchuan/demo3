package com.example.demo.oauth2server.entity;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;

/**
 * Created by dewafer on 2016/12/16.
 */
@Data
@EqualsAndHashCode(of = "authority", callSuper = true)
@ToString(of = "authority", callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "macaw_role_authority_xref")
public class RoleAuthorityXrefEntity extends AbstractAuditable<Long> {

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "authority_id", nullable = false)
    @Where(clause = "disabled = False")
    private AuthorityEntity authority;

}
