package com.ssmsim.dao;

import com.ssmsim.model.Admin;
import com.ssmsim.model.MasterRequest;

import java.util.List;

public interface IAdminDao {

    //Admin getAdmin(String id);
    //测试成功
    String selectAdminPassword(String adminId);

    List<Integer> getRequests();

    MasterRequest getRequestById(int id);

    void acceptRequest(int id);

    void refuseRequest(int id, String reason);
}
