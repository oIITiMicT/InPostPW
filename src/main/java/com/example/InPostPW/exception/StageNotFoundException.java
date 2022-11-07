package com.example.InPostPW.exception;

public class StageNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Stage not found: ";
    private final Long id;

    public StageNotFoundException(Long id) {
        this(DEFAULT_MESSAGE + id, id);
    }

    public StageNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public StageNotFoundException(String message, Long id, Exception e) {
        super(message, e);
        this.id = id;
    }

    public Long getUsername() {
        return id;
    }
}
