package com.ssmsim.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

// 加载spring配置文件
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
public class IStudentServiceTest {

    @Resource
    private IStudentService studentService;

    //测试成功
    @Test
    public void testGetStudentInfo() {
        System.out.println(studentService.getStudentInfo("201530613979"));
    }

//    @Test
//    public void testStudentLogin() {    //测试成功
//        System.out.println(studentService.studentLogin("201530613979", "201530613978"));
//    }

//    @Test
//    public void testModifyPassword() {  //测试成功
//        System.out.println(studentService.modifyPassword("201530613979", "2015", "201530613979"));
//    }
}
