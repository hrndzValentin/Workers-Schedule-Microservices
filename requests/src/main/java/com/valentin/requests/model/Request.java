package com.valentin.requests.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "request_type", length = 50, nullable = false)
    private String requestType;
    @Column(name = "starting_date", nullable = false)
    private LocalDate startingDate;
    @Column(name = "final_date", nullable = false)
    private LocalDate finalDate;
    @Column(name = "request_voucher", nullable = true)
    private String requestVoucher;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus status;
    @Column(name = "response_desc", nullable = false)
    private String responseOrDescription;
    @Column(name = "time_requested", nullable = false)
    private LocalTime timeRequested;
    @Column(name = "employeeId", nullable = false)
    private String employeeId;
}