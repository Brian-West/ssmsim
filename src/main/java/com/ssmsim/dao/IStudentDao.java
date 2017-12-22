package com.ssmsim.dao;

import com.ssmsim.model.Student;
import com.ssmsim.model.StudentRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStudentDao {

    //测试成功
    Student getStudentById(String id);

//    void insertStudentRequest(@Param("stuId") String stuId, @Param("student") Student student);

    boolean insertStudentRequest(@Param("stuId")String studentId, @Param("content")String content);

    List<StudentRequest> getRequest(String id);

//    boolean updateStudentPassword(@Param("stuId")String studentId, @Param("newPwd")String newPwd);

    //测试成功
    String selectStudentPassword(@Param("stuId")String studentId);
}
