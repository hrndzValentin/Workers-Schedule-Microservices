package com.valentin.schedule.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleRequestNotFoundExceptions(RequestNotFoundException exception){
        return Error.builder()
                .status(false)
                .code(400)
                .message(exception.getMessage()).build();
    }

    @ExceptionHandler(EndpointConsumptionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleEndpointExceptions(EndpointConsumptionException exception){
        return Error.builder()
                .status(false)
                .code(400)
                .message(exception.getMessage()).build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handleGenericExceptions(Exception exception){
        return Error.builder()
                .status(false)
                .code(400)
                .message(exception.getMessage()).build();
    }
}