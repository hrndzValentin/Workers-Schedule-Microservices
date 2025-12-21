package com.valentin.requests.repository;

import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    //@Query("SELECT r FROM request r WHERE r.employeeId = :employeeId AND r.status = :status")
    public List<Request> findByEmployeeIdAndStatus(String employeeId, RequestStatus status);
}
