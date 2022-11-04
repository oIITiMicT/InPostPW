package com.example.InPostPW.exception;

public class PackageNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Package not found.";
    private Long packageId;

    public PackageNotFoundException(Long packageId) {
        this(DEFAULT_MESSAGE, packageId);
    }

    public PackageNotFoundException(String message, Long packageId) {
        super(message);
        this.packageId = packageId;
    }

    public PackageNotFoundException(String message, Long packageId, Exception e) {
        super(message, e);
        this.packageId = packageId;
    }

    public Long getPackageId() {
        return packageId;
    }
}
