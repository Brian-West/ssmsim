package com.ssmsim.service.impl;

import com.ssmsim.dao.IAdminDao;
import com.ssmsim.model.Admin;
import com.ssmsim.model.MasterRequest;
import com.ssmsim.service.IAdminService;
import com.ssmsim.utils.GetLoginState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("adminService")
public class AdminService implements IAdminService{

    @Resource
    private IAdminDao adminDao;

    //测试成功
    @Override
    public String adminLogin(String name, String password) {
        String rightPassword = adminDao.selectAdminPassword(name);
        return GetLoginState.checkPassword(rightPassword, password);
    }

    @Override
    public List<Integer> getRequests() {
        return adminDao.getRequests();
    }

    @Override
    public MasterRequest getRequestById(int id) {
        return adminDao.getRequestById(id);
    }

    @Override
    public void acceptRequest(int id) {
        adminDao.acceptRequest(id);
    }

    @Override
    public void refuseRequest(int id, String reason) {
        adminDao.refuseRequest(id, reason);
    }
}
