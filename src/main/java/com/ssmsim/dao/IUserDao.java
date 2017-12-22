package com.ssmsim.dao;

import org.apache.ibatis.annotations.Param;

public interface IUserDao {

    //测试成功
    String selectUserPassword(String userName);

    //测试成功
    boolean updateUserPassword(@Param("userName") String userName, @Param("password") String newPassword);

}