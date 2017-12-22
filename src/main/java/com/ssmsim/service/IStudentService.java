package com.ssmsim.service;

import com.ssmsim.model.Student;

public interface IStudentService {

    Student getStudentInfo(String studentId);

//    void writeStudentRequest(String stuId, Student info);

    boolean writeStudentRequest(String stuId, String content);

    boolean getRequestState(String stuId);

//    boolean modifyPassword(String stuId, String newPwd, String oldPwd);

}
