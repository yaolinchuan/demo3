package com.example.demo.oauth2server.exception;

/**
 * Created by liyuhong on 2017/8/9.
 */
public class Oauth2serverException extends RuntimeException {
    public Oauth2serverException(String msg, Throwable t) {
        super(msg, t);
    }

    public Oauth2serverException(String msg) {
        super(msg);
    }
}
