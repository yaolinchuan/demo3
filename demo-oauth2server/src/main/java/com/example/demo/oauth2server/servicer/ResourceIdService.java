package com.example.demo.oauth2server.servicer;

import com.example.demo.oauth2server.entity.ResourceIdEntity;
import com.example.demo.oauth2server.model.ResourceId;
import com.example.demo.oauth2server.repository.ResourceIdRepository;
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
public class ResourceIdService implements CURDServer<ResourceId, Long> {

    @Autowired
    private ResourceIdRepository resourceIdRepository;

    @Override
    public ResourceId create(ResourceId entity) {
        return resourceIdRepository.save(entity);
    }

    @Override
    public ResourceId put(ResourceId entity) {
        return resourceIdRepository.save(entity);
    }

    @Override
    public boolean delete(Long id) {
        resourceIdRepository.delete(id);
        return true;
    }


    @Override
    public Page<ResourceId> getPage(int page, int size) {
        Pageable pageable = new PageRequest(page, size);
        return resourceIdRepository.findAll(pageable);
    }
}
