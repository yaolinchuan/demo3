package com.example.demo.oauth2server.event;

import com.example.demo.oauth2server.entity.UserEntity;
import com.example.demo.oauth2server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by liyuhong on 2017/8/11.
 */
@RepositoryEventHandler
@Component
public class PwdSaveEvent {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @HandleBeforeCreate
    public void handleUserBeforeCreate(UserEntity entity) {

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    }

    @HandleBeforeSave
    public void handleUserBeforeSave(UserEntity entity) {

        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
    }


//    //创建
//    @Override
//    protected void onBeforeCreate(Object entity) {
//        if (entity instanceof Person){
//
//        }
//    }
//
//    //更新
//    @Override
//    protected void onBeforeSave(Object entity) {
//        if (entity instanceof Person){
//            ((Person) entity).setPwd(null);
//        }
//    }
}
