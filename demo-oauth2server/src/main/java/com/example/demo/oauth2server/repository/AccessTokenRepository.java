package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.AccessTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccessTokenRepository extends JpaRepository<AccessTokenEntity, Long> {

    Optional<AccessTokenEntity> findOneByTokenId(String tokenId);

    Optional<AccessTokenEntity> findOneByAuthenticationId(String authenticationId);

    void deleteByTokenId(String tokenId);

    void deleteByRefreshTokenTokenId(String refreshTokenId);

    List<AccessTokenEntity> findAllByClientIdAndUserName(String clientId, String userName);

    List<AccessTokenEntity> findAllByClientId(String clientId);

}
