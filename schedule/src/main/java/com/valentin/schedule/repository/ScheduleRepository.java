package com.valentin.schedule.repository;

import com.valentin.schedule.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    @Query("""
        SELECT s
        FROM Schedule s
        WHERE s.employeeId = :employeeId
          AND s.date BETWEEN :startDate AND :endDate
    """)
    List<Schedule> findEmployeeScheduleByMonth(
            @Param("employeeId") String employeeId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
