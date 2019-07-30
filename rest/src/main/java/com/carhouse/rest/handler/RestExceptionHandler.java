package com.carhouse.rest.handler;

import com.carhouse.rest.response.ExceptionJSONInfo;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * The Rest exception handler.
 * The handler catches exceptions and gives the appropriate response
 */
@ControllerAdvice
public class RestExceptionHandler {

    private ExceptionJSONInfo response;

    /**
     * Instantiates a new Rest exception handler.
     *
     * @param response the response
     */
    @Autowired
    public RestExceptionHandler(final ExceptionJSONInfo response) {
        this.response = response;
    }

    /**
     * Not found exception handler.
     * Catch not found exception and give the appropriate response
     *
     * @param request the request object to get request url
     * @param ex      the exception object to get exception message
     * @return the response in JSON
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody
    ExceptionJSONInfo notFoundHandler(final HttpServletRequest request, final Exception ex) {
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        return response;
    }

    /**
     * Wrong reference exception handler.
     * Catch wrong reference exception and give the appropriate response
     *
     * @param request the request object to get request url
     * @param ex      the exception object to get exception message
     * @return the response in JSON
     */
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ExceptionHandler(WrongReferenceException.class)
    public @ResponseBody
    ExceptionJSONInfo wrongReferenceHandler(final HttpServletRequest request, final Exception ex) {
        response.setUrl(request.getRequestURL().toString());
        response.setMessage(ex.getMessage());
        return response;
    }
}
