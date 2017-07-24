package com.example.demo.security.resource;

/**
 * Created by liyuhong on 2017/7/19.
 */
public class User {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    String username;
    String pwd;
    public  User(){

    }
}
