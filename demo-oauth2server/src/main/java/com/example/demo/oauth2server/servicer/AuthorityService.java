package com.example.demo.oauth2server.servicer;

import com.example.demo.oauth2server.entity.AuthorityEntity;
import com.example.demo.oauth2server.model.Authority;
import com.example.demo.oauth2server.repository.AuthorityRepository;
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
public class AuthorityService implements CURDServer<Authority, Long> {

    @Autowired
    private AuthorityRepository authorityRepository;


    @Override
    public Authority create(Authority entity) {

        AuthorityEntity authorityEntity = authorityRepository.save(AuthorityEntity.builder().authorityName(entity.getValue()).build());
        return Authority.builder().id(authorityEntity.getId()).value(authorityEntity.getAuthorityName()).build();
    }

    @Override
    public Authority put(Authority entity) {
        AuthorityEntity authorityEntity = authorityRepository.findOne(entity.getId());
        authorityEntity.setAuthorityName(entity.getValue());
        authorityRepository.save(authorityEntity);
        return Authority.builder().id(authorityEntity.getId()).value(authorityEntity.getAuthorityName()).build();
        ;
    }

    @Override
    public boolean delete(Long id) {
        authorityRepository.delete(id);
        return true;
    }


    @Override
    public Page<Authority> getPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return authorityRepository.findAll(pageable);
    }
}
