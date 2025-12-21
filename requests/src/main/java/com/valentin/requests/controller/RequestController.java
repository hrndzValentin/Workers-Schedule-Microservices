package com.valentin.requests.controller;

import com.valentin.requests.exception.EndpointConsumptionException;
import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestDTO;
import com.valentin.requests.service.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestController {

    private final RequestService requestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Request createRequest(@RequestBody RequestDTO requestDTO){
        return requestService.createRequest(requestDTO);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Request> getAllRequests(){
        return requestService.getAllRequests();
    }

    @GetMapping("/status")
    @ResponseStatus(HttpStatus.OK)
    public List<Request> getEmployeeRequestsByStatus(@RequestParam("employeeId") String employeeId, @RequestParam("status") String status){
        if (Set.of("PENDING", "APPROVED", "REJECTED", "EXPIRED").contains(status)) {
            return requestService.getEmployeeRequestsByStatus(employeeId, status);
        }else {
            log.info("throws exception");
            throw new EndpointConsumptionException(status, "APPROVED or REJECTED");
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Request updateRequest(@RequestBody RequestDTO updateRequest, @RequestParam("id") Long id){
        return requestService.updateRequest(updateRequest, id);
    }

    @PostMapping("/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approveOrRejectRequests(@RequestBody HashMap<Long,String> ids, @RequestParam("status") String status){
        if(Set.of("APPROVED","REJECTED").contains(status)){
            requestService.approveOrRejectRequests(ids, status);
        }else {
            throw new EndpointConsumptionException(status, "APPROVED or REJECTED");
        }
    }
}
