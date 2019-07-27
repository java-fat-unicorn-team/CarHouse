package com.carhouse.service.exception;

/**
 * The custom exception.
 * Throws when resource not found
 */
public class NotFoundException extends RuntimeException {
    /**
     * Instantiates a new custom Not found exception.
     *
     * @param message the message to describe exception
     */
    public NotFoundException(final String message) {
        super(message);
    }
}
