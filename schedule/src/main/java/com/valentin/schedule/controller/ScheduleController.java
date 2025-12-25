package com.valentin.schedule.controller;

import com.valentin.schedule.model.Schedule;
import com.valentin.schedule.model.ScheduleDTO;
import com.valentin.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Schedule createSchedule(@RequestBody ScheduleDTO scheduleDTO){
        return scheduleService.createSchedule(scheduleDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Schedule> getScheduleByDates(@RequestParam("employeerId") Long employeerId,
                                            @RequestParam("startDate") LocalDate startDate,
                                            @RequestParam("endDate") LocalDate endDate){
        return scheduleService.getScheduleByDates(employeerId,startDate,endDate);
    }


    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Schedule updateSchedule(@RequestBody ScheduleDTO scheduleDTO, @RequestParam("employeeId") Long employeeId){
        return scheduleService.updateSchedule(scheduleDTO, employeeId);
    }

}
