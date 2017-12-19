package com.ssmsim.service.impl;

import com.ssmsim.dao.IStudentDao;
import com.ssmsim.model.Student;
import com.ssmsim.model.StudentRequest;
import com.ssmsim.service.IStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "studentService")
public class StudentService implements IStudentService{

    @Resource
    private IStudentDao studentDao;

    @Override
    public Student studentLogin(String studentId, String password) {
        Student student = studentDao.getStudentById(studentId);
        if(student != null) {
            if(student.getPassword().equals(password)) {
                student.setState("success");
            } else {
                student.setState("wrongPwd");
            }
        } else {
            student = new Student();
            student.setState("notExist");
        }

        return student;
    }

    @Override
    public void writeStudentRequest(String stuId, Student info) {
        studentDao.insertStudentRequest(stuId, info);
    }

    @Override
    public boolean getRequestState(String stuId) {
        List<StudentRequest> requests = studentDao.getRequest(stuId);
        boolean isDone = true;
        for (StudentRequest request: requests) {
            if(!request.isDone()) {
                isDone = false;
                break;
            }
        }
        return isDone;
    }
}
