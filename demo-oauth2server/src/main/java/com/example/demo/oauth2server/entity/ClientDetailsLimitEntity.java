/*
 * Copyright (c) 2017, Quancheng-ec.com All right reserved. This software is the
 * confidential and proprietary information of Quancheng-ec.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with Quancheng-ec.com.
 */
package com.example.demo.oauth2server.entity;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

/**
 * @author shimingliu 2017年4月1日 下午2:07:02
 * @version ClientLimitEntity.java, v 0.0.1 2017年4月1日 下午2:07:02 shimingliu
 */
@Data
@EqualsAndHashCode(of = {"intervalInMills", "limits"}, callSuper = true)
@ToString(of = {"intervalInMills", "limits"}, callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "client_detail_limit")
public class ClientDetailsLimitEntity extends AbstractPersistable<Long> {

    @Column(name = "intervalInMills")
    private Long intervalInMills;

    @Column(name = "limits")
    private Long limits;

    @OneToOne
    @JoinColumn(name = "client_id")
    private ClientDetailsEntity clientDetail;
}
