package com.ssmsim.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
@Transactional
public class IUserDaoTest {

    @Autowired
    private IUserDao dao;

//    @Test
//    public void testSelectUser() throws Exception {
//        long id = 1;
//        User user = dao.selectUser(id);
//        System.out.println(user.getUsername());
//    }

    //测试成功
    @Test
    public void testSelectUserPassword() {
        System.out.println(dao.selectUserPassword("201530613979"));
    }

    //测试成功
    @Test
    @Rollback()
    public void testUpdateUserPassword() {
        System.out.println(dao.updateUserPassword("201530613979", "201530613979"));
    }

}