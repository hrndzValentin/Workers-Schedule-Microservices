package com.valentin.schedule.service.impl;

import com.valentin.requests.exception.RequestNotFoundException;
import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestDTO;
import com.valentin.requests.model.RequestStatus;
import com.valentin.requests.repository.RequestRepository;
import com.valentin.requests.service.RequestService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;


    @Override
    @Transactional
    public Request createRequest(RequestDTO requestDTO) {
        Request request = Request.builder()
                .requestType(requestDTO.requestType())
                .startingDate(requestDTO.startingDate())
                .finalDate(requestDTO.finalDate())
                .requestVoucher(requestDTO.voucherRequired()? requestDTO.requestVoucher() : "NOT REQUIRED")
                .employeeId(requestDTO.employeeId())
                .status(RequestStatus.PENDING)
                .responseOrDescription("Awaiting for response...")
                .timeRequested(LocalTime.now())
                .build();
        repository.save(request);
        log.info("Request created successfully");
        return request;
    }

    @Override
    public List<Request> getAllRequests() {
        return repository.findAll();
    }

    @Override
    public List<Request> getEmployeeRequestsByStatus(String employeeId, String status) {
        String details = "employeeId: " + employeeId + ", status: " + status;
        RequestStatus mappedStatus = status.equals("PENDING")? RequestStatus.PENDING:
                                            status.equals("APPROVED")? RequestStatus.APPROVED:
                                            status.equals("REJECTED")? RequestStatus.REJECTED:
                                                    RequestStatus.EXPIRED;
        return repository.findByEmployeeIdAndStatus(employeeId,mappedStatus);
    }


    @Override
    @Transactional
    public Request updateRequest(RequestDTO updateRequest, Long id) {
        String details = "id: " + id;
        Request request = repository.findById(id).orElseThrow(() -> new RequestNotFoundException(details));
        request.setRequestType(updateRequest.requestType());
        request.setStartingDate(updateRequest.startingDate());
        request.setFinalDate(updateRequest.finalDate());
        request.setRequestVoucher(updateRequest.requestVoucher());
        request.setTimeRequested(LocalTime.now());

        return repository.save(request);
    }

    @Override
    @Transactional
    public void approveOrRejectRequests(HashMap<Long,String> ids, String status) {
        ids.forEach((key, value) -> {
            String details = "id: " + key;
            Request request = repository.findById(key).orElseThrow(() -> new RequestNotFoundException(details));
            request.setStatus(status.equals("APPROVED")? RequestStatus.APPROVED : RequestStatus.REJECTED);
            request.setResponseOrDescription(value);
            repository.save(request);
        });
    }

}
