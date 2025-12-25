package com.valentin.schedule.service;

import com.valentin.schedule.model.Schedule;
import com.valentin.schedule.model.ScheduleDTO;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleService {
    public Schedule createSchedule(ScheduleDTO scheduleDTO);
    public List<Schedule> getScheduleByDates(Long employeeId, LocalDate startDate, LocalDate endDate);
    public Schedule updateSchedule(ScheduleDTO scheduleDTO, Long id);
}
