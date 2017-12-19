package com.ssmsim.service.impl;

import com.ssmsim.dao.IAdminDao;
import com.ssmsim.model.Admin;
import com.ssmsim.service.IAdminService;

import javax.annotation.Resource;

public class AdminService implements IAdminService{

    @Resource
    private IAdminDao adminDao;

    @Override
    public boolean adminLogin(String name, String pwd) {
        Admin admin = adminDao.getAdmin(name);
        return admin.getUserPassword().equals(pwd);
    }
}
