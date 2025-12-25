package com.valentin.schedule.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "request")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "starting_time", nullable = false)
    private LocalTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;
    @Column(name = "lunch_starting_time", nullable = true)
    private LocalTime lunchStartTime;
    @Column(name = "lunch_end_time", nullable = true)
    private LocalTime lunchEndTime;
    @Column(name = "employeeId", nullable = false)
    private String employeeId;
}