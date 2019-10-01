package com.carhouse.dao.exception;

/**
 * The custom exception.
 * Throws when the json obtained from database is wrong
 */
public class IncorrectJsonException extends RuntimeException {

    /**
     * Instantiates a new custom Incorrect JSON exception.
     *
     * @param message the message to describe exception
     */
    public IncorrectJsonException(final String message) {
        super(message);
    }
}
