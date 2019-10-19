package com.carhouse.rest.handler;

import com.carhouse.rest.response.ExceptionJSONResponse;
import com.carhouse.service.exception.WrongReferenceException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.nio.file.FileSystemException;
import java.util.*;
import java.util.stream.Collectors;

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
        return createResponse(HttpStatus.NOT_FOUND, request.getRequestURI(),
                Collections.singletonList(ex.getMessage()));

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
        return createResponse(HttpStatus.FAILED_DEPENDENCY, request.getRequestURI(),
                Collections.singletonList(ex.getMessage()));
    }
    /**
     * File system exception handler.
     * Catch file system exception and give the appropriate response
     *
     * @param request the request object to get request url
     * @param ex      the exception object to get exception message
     * @return the response in JSON
     */
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(FileSystemException.class)
    public @ResponseBody
    ExceptionJSONResponse fileSystemExceptionHandler(final HttpServletRequest request, final Exception ex) {
        return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, request.getRequestURI(),
                Collections.singletonList(ex.getMessage()));
    }


    /**
     * Handle request body not valid exception json response.
     * Return custom error response object in JSON format.
     *
     * @param request the request object to get request url
     * @param ex      the exception object to get information about exception
     * @return the exception json response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody
    ExceptionJSONResponse handleRequestBodyNotValid(final HttpServletRequest request,
                                                               final MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return createResponse(HttpStatus.BAD_REQUEST, request.getRequestURI(), errors);

    }

    /**
     * Handle path variable not valid exception.
     * Return custom error response object in JSON format.
     *
     * @param request the request object to get request url
     * @param ex      the exception object to get information about exception
     * @return the exception json response
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody
    ExceptionJSONResponse handlePathVariableNotValid(final HttpServletRequest request,
                                                                 final ConstraintViolationException ex) {
        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
        return createResponse(HttpStatus.BAD_REQUEST, request.getRequestURI(), errors);
    }

    /**
     * Create custom response object.
     *
     * @param status     the error status
     * @param requestUri the request uri
     * @param errors     the errors list
     * @return the exception response object
     */
    private ExceptionJSONResponse createResponse(final HttpStatus status, final String requestUri,
                                                 final List<String> errors) {
        response.setDate(new Date());
        response.setStatus(status.value());
        response.setPath(requestUri);
        response.setMessages(errors);
        return response;
    }
}
