package com.shichko.multithreading.exception;

public class ShipPortException extends Exception {
    public ShipPortException() {
        super();
    }

    public ShipPortException(String message) {
        super(message);
    }

    public ShipPortException(String message, Throwable cause) {
        super(message, cause);
    }
}
