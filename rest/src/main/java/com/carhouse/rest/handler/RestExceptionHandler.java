package com.carhouse.rest.handler;

import com.carhouse.rest.response.ExceptionJSONResponse;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * The Rest exception handler.
 * The handler catches exceptions and gives the appropriate response
 */
@ControllerAdvice
public class RestExceptionHandler {

    private ExceptionJSONResponse response;

    /**
     * Instantiates a new Rest exception handler.
     *
     * @param response the response
     */
    @Autowired
    public RestExceptionHandler(final ExceptionJSONResponse response) {
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
    ExceptionJSONResponse notFoundHandler(final HttpServletRequest request, final Exception ex) {
        response.setDate(new Date());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setPath(request.getRequestURI());
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
    ExceptionJSONResponse wrongReferenceHandler(final HttpServletRequest request, final Exception ex) {
        response.setDate(new Date());
        response.setStatus(HttpStatus.FAILED_DEPENDENCY.value());
        response.setPath(request.getRequestURI());
        response.setMessage(ex.getMessage());
        return response;
    }
}
