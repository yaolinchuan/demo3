package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.ScopeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScopeRepository extends JpaRepository<ScopeEntity, Long> {

    Optional<ScopeEntity> findOneByValue(String value);
}
