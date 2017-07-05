package com.example.demo.task.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by liyuhong on 2017/7/5.
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskRunOutput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String output;
    public TaskRunOutput(String output){
        this.output =output;
    }

}