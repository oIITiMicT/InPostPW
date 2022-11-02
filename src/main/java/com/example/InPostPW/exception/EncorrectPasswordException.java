package com.example.InPostPW.exception;

public class EncorrectPasswordException extends RuntimeException {

    public EncorrectPasswordException(String message, Exception e) {
        super(message, e);
    }

    public EncorrectPasswordException(String message) {
        super(message);
    }

}
