package com.example.demo.oauth2server.servicer;

import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liyuhong on 2017/8/9.
 */
public interface CURDServer<TModel extends Serializable, PK extends Serializable> {

    TModel create(TModel entity);

    TModel put(TModel entity);

    boolean delete(PK id);

    Page<TModel> getPage(int page, int size);
}
