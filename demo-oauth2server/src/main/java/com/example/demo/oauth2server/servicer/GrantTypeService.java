package com.example.demo.oauth2server.servicer;

import com.example.demo.oauth2server.entity.GrantTypeEntity;
import com.example.demo.oauth2server.model.GrantType;
import com.example.demo.oauth2server.repository.GrantTypeRepository;
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
public class GrantTypeService implements CURDServer<GrantType, Long> {

    @Autowired
    private GrantTypeRepository grantTypeRepository;

    @Override
    public GrantType create(GrantType entity) {
        return grantTypeRepository.save(entity);
    }

    @Override
    public GrantType put(GrantType entity) {
        return grantTypeRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        grantTypeRepository.delete(id);
        return true;
    }


    @Override
    public Page<GrantType> getPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return grantTypeRepository.findAll(pageable);
    }
}
