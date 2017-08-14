package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.ClientDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "clientDetail", path = "clientDetail")
public interface ClientDetailsRepository extends JpaRepository<ClientDetailsEntity, Long> {

    Optional<ClientDetailsEntity> findOneByClientId(@Param("clientId") @RequestParam("clientId") String clientId);
}
