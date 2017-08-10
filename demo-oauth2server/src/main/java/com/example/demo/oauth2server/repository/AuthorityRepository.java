package com.example.demo.oauth2server.repository;

import com.example.demo.oauth2server.entity.AccessTokenEntity;
import com.example.demo.oauth2server.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by liyuhong on 2017/8/9.
 */
@RepositoryRestResource(collectionResourceRel = "authority", path = "authority")
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
