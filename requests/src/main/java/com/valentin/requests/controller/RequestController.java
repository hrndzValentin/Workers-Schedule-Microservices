package com.valentin.requests.controller;

import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestDTO;
import com.valentin.requests.service.RequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Request updateRequest(@RequestBody RequestDTO updateRequest, @RequestParam("id") Long id){
        return requestService.updateRequest(updateRequest, id);
    }
}
