package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.RedirectUriEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "redirectUri", path = "redirectUri")
public interface RedirectUriRepository extends JpaRepository<RedirectUriEntity, Long> {

    Optional<RedirectUriEntity> findOneByValue(String value);
}
