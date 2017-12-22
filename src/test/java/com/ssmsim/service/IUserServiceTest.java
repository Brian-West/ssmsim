package com.ssmsim.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
@Transactional
public class IUserServiceTest {

    @Resource IUserService service;

    //测试成功
    @Test
    public void testUserLogin() {
        System.out.println(service.userLogin("201530613979", "2015"));
    }

    //测试成功
    @Test
    @Rollback
    public void testModifyUserPassword() {
        System.out.println(service.modifyUserPassword("201530613979", "20153061397", "2015"));
    }
}
