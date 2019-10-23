package com.carhouse.service.exception;

/**
 * The custom exception.
 * Throws when the file write failed
 */
public class WriteFileException  extends RuntimeException {
    /**
     * Instantiates a new custom write file exception.
     *
     * @param message the message to describe exception
     */
    public WriteFileException(final String message) {
        super(message);
    }
}
