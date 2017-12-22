package com.ssmsim.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IStudentDaoTest {

    @Resource
    private IStudentDao dao;

    @Test
    public void testGetStudentById() {  // 测试成功
        System.out.println(dao.getStudentById("201530613979").toString());
    }

    @Test
    public void testSelectStudentPassword() {   //测试成功
        System.out.println(dao.selectStudentPassword("201530613979"));
    }


}
