package com.shichko.secondtask.exception;

public class TariffXmlException extends Exception {
    public TariffXmlException() {
        super();
    }

    public TariffXmlException(String message) {
        super(message);
    }

    public TariffXmlException(String message, Throwable cause) {
        super(message, cause);
    }

    public TariffXmlException(Throwable cause) {
        super(cause);
    }
}
