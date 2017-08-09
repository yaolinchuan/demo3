package com.example.demo.oauth2server.servicer;

import com.example.demo.oauth2server.entity.UserEntity;
import com.example.demo.oauth2server.model.User;
import com.example.demo.oauth2server.repository.UserRepository;
import com.example.demo.oauth2server.repository.UserRoleXrefRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by liyuhong on 2017/8/9.
 */
@Service
@Transactional
public class UserService implements CURDServer<User, Long> {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleXrefRepository userRoleXrefRepository;

    @Override
    public User create(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User put(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        userRepository.delete(id);
        return true;
    }


    @Override
    public Page<User> getPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return userRepository.findAll(pageable).map(userEntity -> User.builder().id(userEntity.getId()).username(userEntity.getUsername()).build());
    }


}
