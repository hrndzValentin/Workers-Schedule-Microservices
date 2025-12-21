package com.valentin.requests.exception;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(String id) {
        super("The next id:" + id + "couldn't be found in our database");
    }
}
