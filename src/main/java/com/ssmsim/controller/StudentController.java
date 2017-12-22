package com.ssmsim.controller;

import com.alibaba.fastjson.JSONArray;
import com.ssmsim.model.Student;
import com.ssmsim.service.IStudentService;
import com.ssmsim.service.IUserService;
import com.ssmsim.viewObject.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/student")
@CrossOrigin("http://localhost:8081")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @Resource
    private IUserService userService;

//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    @ResponseBody
//    public Student studentLogin(@RequestParam("userName")String userName, @RequestParam("pwd")String password, HttpSession session) {
//        Student loginStudent = studentService.studentLogin(userName, password);
//        if(loginStudent.getState().equals("success")) {
//            session.setAttribute("studentId", loginStudent.getPassword());
//        }
//        return loginStudent;
//    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map studentLogin(@RequestBody LoginUser user, HttpSession session) {
        //String state = studentService.studentLogin(user.getUserName(), user.getPassword());
        String state = userService.userLogin(user.getUserName(), user.getPassword());
        if(state.equals("success")) {
            session.setAttribute("studentId", user.getUserName());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("valid", state);
        return result;
    }

//    @RequestMapping(value = "/validateCodeMet", method = RequestMethod.GET)
//    @ResponseBody
//    public Map validateCodeMet(HttpSession session, @RequestParam("loginCode") String code) {
//        String realCode = (String) session.getAttribute("rand");
//        System.out.println("realCode" + realCode);
//        System.out.println("loginCode" + code);
//        Map<String, Object> result = new HashMap<>();
//        //result.put("valid", studentService.isValidateCodeMet(realCode, code));
//        result.put("valid", true);
//        return result;
//    }

//    @RequestMapping(value = "/validateCodeMet", method = RequestMethod.POST)
//    @ResponseBody
//    public Map validateCodeMet(HttpSession session, @RequestBody String payload) {
//        String realCode = (String) session.getAttribute("rand");
//        System.out.println("realCode" + realCode);
//        System.out.println(payload);
//        Map<String, Object> result = new HashMap<>();
//        result.put("valid", true);
//        return result;
//    }

    @RequestMapping(value = "info", method = RequestMethod.GET)
    @ResponseBody
    public Student showStudentInfo(HttpSession session) {
        String studentId = (String)session.getAttribute("studentId");
        //return studentService.getStudentInfo(studentId);
        return studentService.getStudentInfo("201530613979");
    }

    //每个学生一次申请
    @RequestMapping(value = "request", method = RequestMethod.POST)
    @ResponseBody
    public Boolean getRequestState(HttpSession session) {
        String studentId = (String) session.getAttribute("studentId");
        return studentService.getRequestState(studentId);
    }

    @RequestMapping(value = "pwdModify", method = RequestMethod.POST)
    @ResponseBody
    public Map modifyPassword(HttpSession session, @RequestParam("oldPwd")String oldPwd, @RequestParam("newPwd")String newPwd) {
        String userName = (String) session.getAttribute("studentId");
        String state = userService.modifyUserPassword(userName, oldPwd, newPwd);
        if (state.equals("success")) {
            session.removeAttribute("studentId");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("valid", state);
        return result;
        //return studentService.modifyPassword(userId, newPwd, oldPwd);
    }

    @RequestMapping(value = "modify", method = RequestMethod.POST)
    @ResponseBody
    public Boolean studentModify(@RequestBody JSONArray info, HttpSession session) {
        String studentId = (String)session.getAttribute("studentId");
        StringBuilder string = new StringBuilder();
        for (Object property: info) {
            LinkedHashMap map = (LinkedHashMap)property;
            Set keys = map.keySet();
            for (Object key: keys) {
                String value = (String) map.get(key);
                string.append("将").append((String) key).append("改为").append(value).append(";");
            }
        }
        String content = string.toString();
        return studentService.writeStudentRequest(studentId, content);
    }

    // 待修改，接受字符串而不是Student对象如何？？？
//    @RequestMapping(value = "modify", method = RequestMethod.POST)
//    @ResponseBody
//    public Boolean studentModify(@RequestBody Student modifiedStudent, HttpSession session) {
//        String studentId = (String)session.getAttribute("studentId");
//        studentService.writeStudentRequest(studentId, modifiedStudent);
//        return true;
//    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public Boolean studentLogout(HttpSession session) {
        session.removeAttribute("studentId");
        return true;
    }

//    /**
//     * 通过url请求返回图像的字节流
//     */
//    @RequestMapping("/verifyCode")
//    public void getVerifyCodeAndPic(HttpSession session, HttpServletResponse response) {
//
//        // 生成随机字串
//        String verifyCode = ValidateCode.generateVerifyCode(4);
//        System.out.println("genCode" + verifyCode);
//        // 存入会话session
//        session.setAttribute("rand", verifyCode.toLowerCase());
//
//        ServletOutputStream out;
//
//        // 生成图片
//        int w = 80, h = 34;
//        response.setContentType("multipart/form-data");
//        try {
//            out = response.getOutputStream();
//            ValidateCode.outputImage(w, h, out, verifyCode);
//            out.flush();
//        }catch (IOException e){
//            System.out.println("验证码图片加载错误");
//        }
//    }
}
