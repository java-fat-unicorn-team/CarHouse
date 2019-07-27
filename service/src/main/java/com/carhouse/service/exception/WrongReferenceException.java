package com.carhouse.service.exception;

/**
 * The custom exception.
 * Throws when the object you are trying to add has wrong references
 */
public class WrongReferenceException extends RuntimeException {
    /**
     * Instantiates a new custom Wrong reference exception.
     *
     * @param message the message to describe exception
     */
    public WrongReferenceException(final String message) {
        super(message);
    }
}
