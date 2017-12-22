package com.ssmsim.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IAdminDaoTest {

    @Resource
    private IAdminDao dao;

    //测试成功
    @Test
    public void testSelectAdminPassword() {
        System.out.println(dao.selectAdminPassword("yang"));
    }
}
