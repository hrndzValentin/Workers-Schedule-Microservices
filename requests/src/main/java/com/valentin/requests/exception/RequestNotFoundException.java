package com.valentin.requests.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(String details) {
        super("The request with the next details " + details + "couldn't be found in our database");
    }
}
