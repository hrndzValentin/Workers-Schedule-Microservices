package com.valentin.requests.service;

import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestDTO;

import java.util.List;

public interface RequestService {

    public Request createRequest(RequestDTO requestDTO);

    public List<Request> getAllRequests();

    public Request updateRequest(RequestDTO updateRequest, Long id);
}
