package com.ssmsim.controller;

import com.ssmsim.model.Student;
import com.ssmsim.service.IStudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Student studentLogin(@RequestParam("userName")String userName, @RequestParam("pwd")String password, HttpSession session) {
        Student loginStudent = studentService.studentLogin(userName, password);
        if(loginStudent.getState().equals("success")) {
            session.setAttribute("studentId", loginStudent.getPassword());
        }
        return loginStudent;
    }

    @RequestMapping(value = "request", method = RequestMethod.POST)
    @ResponseBody
    public Boolean getRequestState(@RequestParam("userName")String userName) {
        return studentService.getRequestState(userName);
    }

    // 待修改
    @RequestMapping(value = "modify", method = RequestMethod.POST)
    @ResponseBody
    public Boolean studentModify(@RequestBody Student modifiedStudent, HttpSession session) {
        String studentId = (String)session.getAttribute("studentId");
        studentService.writeStudentRequest(studentId, modifiedStudent);
        return true;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public Boolean studentLogout(HttpSession session) {
        session.removeAttribute("studentId");
        return true;
    }
}
