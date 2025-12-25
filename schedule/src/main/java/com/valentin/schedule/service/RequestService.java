package com.valentin.schedule.service;

import com.valentin.requests.model.Request;
import com.valentin.requests.model.RequestDTO;

import java.util.HashMap;
import java.util.List;

public interface RequestService {

    public Request createRequest(RequestDTO requestDTO);

    public List<Request> getAllRequests();

    public List<Request> getEmployeeRequestsByStatus(String employeeId, String status);

    public Request updateRequest(RequestDTO updateRequest, Long id);

    public void approveOrRejectRequests(HashMap<Long,String> ids, String status);
}
