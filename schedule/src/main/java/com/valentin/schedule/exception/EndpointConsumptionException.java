package com.valentin.schedule.exception;

public class EndpointConsumptionException extends RuntimeException{

    public EndpointConsumptionException(String mistakes, String right) {
        super("the next consumption params are in the wrong format: " + mistakes + " they should be like: " + right);
    }
}
