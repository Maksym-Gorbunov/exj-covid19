package com.mg.covid19.config.exception.exc;


public class ResourceCreationException extends Exception {

    public ResourceCreationException(String message) {
        super(message);
    }

    public ResourceCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
