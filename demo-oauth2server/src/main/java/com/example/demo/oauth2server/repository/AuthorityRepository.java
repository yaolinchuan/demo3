package com.example.demo.oauth2server.repository;

import com.example.demo.oauth2server.entity.AccessTokenEntity;
import com.example.demo.oauth2server.entity.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liyuhong on 2017/8/9.
 */
public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long> {
}
