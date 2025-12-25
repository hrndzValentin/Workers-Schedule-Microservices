package com.valentin.schedule.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(String details) {
        super("The schedule with the next details " + details + " couldn't be found in our database");
    }
}
