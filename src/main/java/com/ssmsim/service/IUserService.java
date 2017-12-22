package com.ssmsim.service;

public interface IUserService {

    String userLogin(String userName, String password);

    String modifyUserPassword(String userName, String oldPwd, String newPwd);

}