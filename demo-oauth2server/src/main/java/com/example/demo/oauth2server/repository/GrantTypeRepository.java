package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.GrantTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrantTypeRepository extends JpaRepository<GrantTypeEntity, Long> {

    Optional<GrantTypeEntity> findOneByValue(String value);
}
