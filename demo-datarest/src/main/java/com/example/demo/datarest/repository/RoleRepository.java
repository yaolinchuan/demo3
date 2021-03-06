package com.example.demo.datarest.repository;

import com.example.demo.datarest.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import javax.transaction.Transactional;

/**
 * Created by liyuhong on 2017/8/10.
 */
@RepositoryRestResource(collectionResourceRel = "role", path = "role")
//@Transactional
public interface RoleRepository extends JpaRepository<Role, Long> {
}
