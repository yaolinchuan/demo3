package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.UserRoleXrefEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "userRoleXre", path = "userRoleXre")
public interface UserRoleXrefRepository extends JpaRepository<UserRoleXrefEntity, Long> {
}
