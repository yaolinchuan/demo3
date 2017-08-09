package com.example.demo.oauth2server.model;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by liyuhong on 2017/8/9.
 */
@Data
@EqualsAndHashCode(of = {}, callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
public class User implements Serializable {

    private Long id;
    private String username;
    private boolean disabled;
}
