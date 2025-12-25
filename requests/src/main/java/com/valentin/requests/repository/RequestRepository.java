package com.valentin.requests.repository;

import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestDTO;
import com.valentin.requests.model.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("""
        SELECT new com.valentin.requests.model.RequestDTO(
            r.requestType,
            r.startingDate,
            r.finalDate,
            CASE
                WHEN r.requestVoucher ='NOT REQUIRED' THEN false
                ELSE true
            END,
            r.requestVoucher,
            r.employeeId
        )
        FROM Request r
        WHERE r.employeeId = :employeeId
          AND r.status = :status
    """)
    List<RequestDTO> findByEmployeeDtos(
            @Param("employeeId") String employeeId,
            @Param("status") RequestStatus status
    );
    //public List<RequestDTO> findByEmployeeIdAndStatus(String employeeId, RequestStatus status);
}
