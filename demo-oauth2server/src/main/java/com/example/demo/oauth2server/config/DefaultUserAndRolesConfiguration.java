package com.example.demo.oauth2server.config;


import com.example.demo.oauth2server.entity.RoleEntity;
import com.example.demo.oauth2server.entity.UserEntity;
import com.example.demo.oauth2server.entity.UserRoleXrefEntity;
import com.example.demo.oauth2server.repository.RoleRepository;
import com.example.demo.oauth2server.repository.UserRepository;
import com.example.demo.oauth2server.repository.UserRoleXrefRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@Profile("dev")
public class DefaultUserAndRolesConfiguration implements InitializingBean {

    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = "admin888";

    private static final String DEFAULT_USER_USERNAME = "user";
    private static final String DEFAULT_USER_PASSWORD = "user888";

    private static final String[] DEFAULT_ROLES = {"ADMIN", "USER"};

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleXrefRepository userRoleXrefRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void afterPropertiesSet() throws Exception {
        if (userRepository.count() == 0) {
            List<RoleEntity> defaultRoleEntities = new ArrayList<>();
            Arrays.stream(DEFAULT_ROLES).forEach(role -> defaultRoleEntities.add(roleRepository.findOneByName(role).orElseGet(() -> roleRepository.save(RoleEntity.builder().name(role).build()))));

            UserEntity defaultAdminUserEntity = userRepository.findOneByUsername(DEFAULT_ADMIN_USERNAME).orElseGet(() -> userRepository.save(UserEntity.builder().username(DEFAULT_ADMIN_USERNAME).password(passwordEncoder.encode(DEFAULT_ADMIN_PASSWORD)).build()));

            defaultRoleEntities.stream().forEach(roleEntity -> userRoleXrefRepository.save(UserRoleXrefEntity.builder().user(defaultAdminUserEntity).role(roleEntity).build()));

            userRepository.findOneByUsername(DEFAULT_USER_USERNAME).orElseGet(() -> {
                UserEntity userEntity = UserEntity.builder().username(DEFAULT_USER_USERNAME).password(passwordEncoder.encode(DEFAULT_USER_PASSWORD)).build();

                roleRepository.findOneByName("USER").ifPresent(roleEntity -> userEntity.setRoles(Collections.singleton(UserRoleXrefEntity.builder().user(userEntity).role(roleEntity).build())));

                return userRepository.save(userEntity);
            });
        }
    }
}
