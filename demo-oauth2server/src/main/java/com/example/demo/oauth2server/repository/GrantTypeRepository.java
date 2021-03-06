package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.GrantTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "grantType", path = "grantType")
public interface GrantTypeRepository extends JpaRepository<GrantTypeEntity, Long> {

    Optional<GrantTypeEntity> findOneByValue(@Param("value") @RequestParam("value") String value);
}
