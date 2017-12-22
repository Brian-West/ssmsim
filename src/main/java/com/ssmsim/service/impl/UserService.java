package com.ssmsim.service.impl;

import com.ssmsim.dao.IUserDao;
import com.ssmsim.model.User;
import com.ssmsim.service.IUserService;
import com.ssmsim.utils.GetLoginState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserService implements IUserService {

    @Resource
    private IUserDao userDao;

    //测试成功
    @Override
    public String userLogin(String userName, String password) {
        String rightPassword = userDao.selectUserPassword(userName);
        return GetLoginState.checkPassword(rightPassword, password);
    }

    //测试成功
    @Override
    public String modifyUserPassword(String userName, String oldPwd, String newPwd) {
        String rightPassword = userDao.selectUserPassword(userName);
        if (rightPassword.equals(oldPwd)) {
            boolean flag = userDao.updateUserPassword(userName, newPwd);
            if (flag) {
                return "success";
            } else {
                return "failed";
            }
        } else {
            return "wrongPwd";
        }
    }

}