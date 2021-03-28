package com.shichko.first_task.exception;

public class FileReadException extends Exception {

    public FileReadException(String message) {
        super(message);
    }

    public FileReadException(Throwable cause) {
        super(cause);
    }

    public FileReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
