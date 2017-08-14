package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "resourceId", path = "resourceId")
public interface ResourceRepository extends JpaRepository<ResourceEntity, Long> {

    Optional<ResourceEntity> findOneByValue(@Param("value") @RequestParam("value") String value);
}
