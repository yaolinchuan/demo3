package com.example.demo.oauth2server.servicer;

import com.example.demo.oauth2server.entity.*;
import com.example.demo.oauth2server.model.Role;
import com.example.demo.oauth2server.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by liyuhong on 2017/8/9.
 */
@Service
@Transactional
public class RoleService implements CURDServer<Role, Long> {


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role create(Role entity) {
        entity = roleRepository.save(entity);
        return Role.builder().disabled(entity.isDisabled()).name(entity.getName()).id(entity.getId()).build();
    }

    @Override
    public Role put(Role entity) {

        entity = roleRepository.save(entity);
        return Role.builder().disabled(entity.isDisabled()).name(entity.getName()).id(entity.getId()).build();
    }

    @Override
    public boolean delete(Long id) {
        roleRepository.delete(id);
        return true;
    }


    @Override
    public Page<Role> getPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return roleRepository.findAll(pageable).map(entity ->
                Role.builder().disabled(entity.isDisabled()).name(entity.getName()).id(entity.getId()).build());
    }


}
