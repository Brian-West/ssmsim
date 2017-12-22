package com.ssmsim.service;

import com.ssmsim.model.MasterRequest;

import java.util.List;

public interface IAdminService {

    String adminLogin(String name, String password);

    List<Integer> getRequests();

    MasterRequest getRequestById(int id);

    void acceptRequest(int id);

    void refuseRequest(int id, String reason);
}
