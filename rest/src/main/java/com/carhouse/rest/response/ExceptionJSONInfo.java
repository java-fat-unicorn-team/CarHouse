package com.carhouse.rest.response;

import org.springframework.stereotype.Component;

/**
 * The class is used to create response on the exceptions.
 */
@Component
public class ExceptionJSONInfo {
    private String message;
    private String url;

    /**
     * Instantiates a new Exception json info.
     */
    public ExceptionJSONInfo() {
    }

    /**
     * Gets message.
     *
     * @return the message describing the exception
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message describing the exception
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Gets url.
     *
     * @return the url of the request at which the exception was thrown
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets url.
     *
     * @param url the url of the request at which the exception was thrown
     */
    public void setUrl(final String url) {
        this.url = url;
    }
}
