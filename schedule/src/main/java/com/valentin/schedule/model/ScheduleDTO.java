package com.valentin.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.time.LocalTime;

public record ScheduleDTO(@JsonFormat(pattern = "MM/dd/yyyy") LocalDate date,
                         @JsonFormat(pattern = "HH:mm") LocalTime startTime,
                         @JsonFormat(pattern = "HH:mm") LocalTime endTime,
                         @JsonFormat(pattern = "HH:mm") LocalTime lunchTime,
                         String employeeId) {
}