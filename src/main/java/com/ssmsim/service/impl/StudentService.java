package com.ssmsim.service.impl;

import com.ssmsim.dao.IStudentDao;
import com.ssmsim.model.Student;
import com.ssmsim.model.StudentRequest;
import com.ssmsim.service.IStudentService;
import com.ssmsim.utils.GetLoginState;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "studentService")
public class StudentService implements IStudentService{

    @Resource
    private IStudentDao studentDao;

    //测试成功
    @Override
    public Student getStudentInfo(String studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public boolean writeStudentRequest(String stuId, String content) {
        return studentDao.insertStudentRequest(stuId, content);
    }

//    @Override
//    public void writeStudentRequest(String stuId, Student info) {
//        studentDao.insertStudentRequest(stuId, info);
//    }

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

//    @Override
//    public boolean modifyPassword(String stuId, String newPwd, String oldPwd) {
//        String pwd = studentDao.selectStudentPassword(stuId);
//        return pwd.equals(oldPwd) && studentDao.updateStudentPassword(stuId, newPwd);
//    }
}
