package com.example.demo.oauth2server.servicer;

import com.example.demo.oauth2server.entity.ScopeEntity;
import com.example.demo.oauth2server.model.Scope;
import com.example.demo.oauth2server.repository.ScopeRepository;
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
public class ScopeService implements CURDServer<Scope, Long> {

    @Autowired
    private ScopeRepository scopeRepository;

    @Override
    public Scope create(Scope entity) {
        return scopeRepository.save(entity);
    }

    @Override
    public Scope put(Scope entity) {

        return scopeRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        scopeRepository.delete(id);
        return true;
    }


    @Override
    public Page<Scope> getPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return scopeRepository.findAll(pageable);
    }
}
