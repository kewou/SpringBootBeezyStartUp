package org.example.exceptions;

public class NoInstanceFoundException extends RuntimeException {

    public NoInstanceFoundException(String message) {
        super(message);
    }

    public NoInstanceFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
