package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.GrantTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "grantType", path = "grantType")
public interface GrantTypeRepository extends JpaRepository<GrantTypeEntity, Long> {

    Optional<GrantTypeEntity> findOneByValue(String value);
}
