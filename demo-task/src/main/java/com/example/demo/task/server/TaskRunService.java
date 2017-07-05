package com.example.demo.task.server;

import com.example.demo.task.entity.TaskRunOutput;
import com.example.demo.task.repository.TaskRunOutputResitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liyuhong on 2017/7/5.
 */
@Component
@Slf4j
public class TaskRunService {
    @Autowired
    private TaskRunOutputResitory taskRunOutputResitory;
    @BeforeTask
    public void init(TaskExecution taskExecution) {
        String execDate = new SimpleDateFormat().format(new Date());
        taskRunOutputResitory.save(new TaskRunOutput("Executed at " + execDate));
        log.info("Executed at : " + execDate);
    }

}
