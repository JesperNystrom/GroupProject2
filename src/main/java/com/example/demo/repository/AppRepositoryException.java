package com.example.demo.repository;

public class AppRepositoryException extends RuntimeException {

    public AppRepositoryException() {
    }

    public AppRepositoryException(String message) {
        super(message);
    }

    public AppRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRepositoryException(Throwable cause) {
        super(cause);
    }

    public AppRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
