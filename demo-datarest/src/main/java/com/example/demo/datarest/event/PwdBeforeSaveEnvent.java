package com.example.demo.datarest.event;

import com.example.demo.datarest.entity.Person;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.data.rest.core.event.AbstractRepositoryEventListener;
import org.springframework.data.rest.core.event.BeforeSaveEvent;
import org.springframework.stereotype.Component;

/**
 * Created by liyuhong on 2017/8/11.
 */
@RepositoryEventHandler
@Component
public class PwdBeforeSaveEnvent {

    @HandleBeforeSave
    @HandleBeforeCreate
    public void handlePersonSave(Person entity) {
        entity.setPwd(entity.getPwd() + "加密");
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
