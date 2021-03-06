package com.example.demo.oauth2server.repository;


import com.example.demo.oauth2server.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.server.PathParam;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOneByUsername(@Param("username") @RequestParam("username") String username);

}
