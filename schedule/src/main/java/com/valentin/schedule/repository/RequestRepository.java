package com.valentin.schedule.repository;

import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    //@Query("SELECT r FROM request r WHERE r.employeeId = :employeeId AND r.status = :status")
    public List<Request> findByEmployeeIdAndStatus(String employeeId, RequestStatus status);
}
