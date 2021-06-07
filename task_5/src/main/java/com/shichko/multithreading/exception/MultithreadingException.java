package com.shichko.multithreading.exception;

public class MultithreadingException extends Exception {
    public MultithreadingException() {
        super();
    }

    public MultithreadingException(String message) {
        super(message);
    }

    public MultithreadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
