package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.UserRoleXrefEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleXrefRepository extends JpaRepository<UserRoleXrefEntity, Long> {
}
