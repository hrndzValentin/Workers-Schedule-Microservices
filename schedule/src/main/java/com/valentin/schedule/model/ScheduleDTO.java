package com.valentin.schedule.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

public record ScheduleDTO(String requestType,
                         @JsonFormat(pattern = "MM/dd/yyyy") LocalDate startingDate,
                         @JsonFormat(pattern = "MM/dd/yyyy") LocalDate finalDate,
                         boolean voucherRequired,
                         @Nullable String requestVoucher,
                         String employeeId) {
}
