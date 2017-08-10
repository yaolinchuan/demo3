package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "refreshToken", path = "refreshToken")
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findOneByTokenId(String tokenId);

    void deleteByTokenId(String tokenId);
}
