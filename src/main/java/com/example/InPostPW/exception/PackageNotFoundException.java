package com.example.InPostPW.exception;

public class PackageNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Package not found.";
    private Long id;

    public PackageNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public PackageNotFoundException(Long id) {
        this(DEFAULT_MESSAGE, id);
    }

    public PackageNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public PackageNotFoundException(String message, Long id, Exception e) {
        super(message, e);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
