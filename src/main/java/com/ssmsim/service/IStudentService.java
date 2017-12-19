package com.ssmsim.service;

import com.ssmsim.model.Student;

public interface IStudentService {

    Student studentLogin(String studentId, String password);

    void writeStudentRequest(String stuId, Student info);

    boolean getRequestState(String stuId);

}
