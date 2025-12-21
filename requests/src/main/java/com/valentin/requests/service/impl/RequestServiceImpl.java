package com.valentin.requests.service.impl;

import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestDTO;
import com.valentin.requests.repository.RequestRepository;
import com.valentin.requests.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    private final RequestRepository repository;


    @Override
    public Request createRequest(RequestDTO requestDTO) {
        Request request = Request.builder()
                .requestType(requestDTO.requestType())
                .startingDate(requestDTO.startingDate())
                .finalDate(requestDTO.finalDate())
                .requestVoucher(requestDTO.voucherRequired()? requestDTO.requestVoucher() : "NOT REQUIRED")
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
}
