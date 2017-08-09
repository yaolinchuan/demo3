package com.example.demo.oauth2server.resource;

import org.springframework.data.domain.Page;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyuhong on 2017/8/9.
 */
public interface CURDController<TModel extends Serializable, PK extends Serializable> {

    TModel post(TModel entity);

    TModel put(TModel entity);

    boolean delete(PK id);

    Page<TModel> getPage(int page, int size);
}
