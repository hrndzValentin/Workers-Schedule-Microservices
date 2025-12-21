package com.valentin.requests.repository;

import com.valentin.requests.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query("SELECT * FROM request WHERE status = 'PENDING'")
    public Optional<List<Request>> findPendingRequests();
}
