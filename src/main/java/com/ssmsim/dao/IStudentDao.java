package com.ssmsim.dao;

import com.ssmsim.model.Student;
import com.ssmsim.model.StudentRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IStudentDao {

    Student getStudentById(String id);

    void insertStudentRequest(@Param("stuId") String stuId, @Param("student") Student student);

    List<StudentRequest> getRequest(String id);
}
