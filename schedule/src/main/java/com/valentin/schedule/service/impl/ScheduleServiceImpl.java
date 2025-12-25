package com.valentin.schedule.service.impl;

import com.valentin.schedule.model.Schedule;
import com.valentin.schedule.model.ScheduleDTO;
import com.valentin.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    @Override
    public Schedule createSchedule(ScheduleDTO scheduleDTO) {
        return null;
    }

    @Override
    public List<Schedule> getScheduleByDates(Long employeeId, LocalDate startDate, LocalDate endDate) {
        return List.of();
    }

    @Override
    public Schedule updateSchedule(ScheduleDTO scheduleDTO, Long id) {
        return null;
    }
}
