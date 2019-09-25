package com.carhouse.rest.handler;

import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
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

    /**
     * Not found exception handler.
     * Catch not found exception and give the appropriate response
     *
     * @param request the request object to get request url
     * @param ex      the exception object to get exception message
     * @return the response as string
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody
    String notFoundHandler(final HttpServletRequest request, final Exception ex) {
        return "\"date\":\"" + new Date()
                + "\", \"status\":\"" + HttpStatus.NOT_FOUND.value()
                + "\", \"message\":\"" + ex.getMessage()
                + "\", \"path\":\"" + request.getRequestURI()
                + '\"';
    }

    /**
     * Wrong reference exception handler.
     * Catch wrong reference exception and give the appropriate response
     *
     * @param request the request object to get request url
     * @param ex      the exception object to get exception message
     * @return the response as string
     */
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @ExceptionHandler(WrongReferenceException.class)
    public @ResponseBody
    String wrongReferenceHandler(final HttpServletRequest request, final Exception ex) {
        return "\"date\":\"" + new Date()
                + "\", \"status\":\"" + HttpStatus.FAILED_DEPENDENCY.value()
                + "\", \"message\":\"" + ex.getMessage()
                + "\", \"path\":\"" + request.getRequestURI()
                + '\"';
    }
}
