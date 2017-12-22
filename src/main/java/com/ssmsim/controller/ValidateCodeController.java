package com.ssmsim.controller;

import com.ssmsim.utils.ValidateCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin("http://localhost:8081")
public class ValidateCodeController {

    /**
     * 通过url请求返回图像的字节流
     */
    @RequestMapping("/verifyCode")
    public void getVerifyCodeAndPic(HttpSession session, HttpServletResponse response) {

        // 生成随机字串
        String verifyCode = ValidateCode.generateVerifyCode(4);
        System.out.println("genCode" + verifyCode);
        // 存入会话session
        session.setAttribute("rand", verifyCode.toLowerCase());

        ServletOutputStream out;

        // 生成图片
        int w = 80, h = 34;
        response.setContentType("multipart/form-data");
        try {
            out = response.getOutputStream();
            ValidateCode.outputImage(w, h, out, verifyCode);
            out.flush();
        }catch (IOException e){
            System.out.println("验证码图片加载错误");
        }
    }

    @RequestMapping(value = "/validateCodeMet", method = RequestMethod.GET)
    @ResponseBody
    public Map validateCodeMet(HttpSession session, @RequestParam("loginCode") String code) {
        String realCode = (String) session.getAttribute("rand");
        System.out.println("realCode" + realCode);
        System.out.println("loginCode" + code);
        Map<String, Object> result = new HashMap<>();
        //result.put("valid", studentService.isValidateCodeMet(realCode, code));
        //result.put("valid", ValidateCode.isValidateCodeMet(realCode, code));
        result.put("valid", true);
        return result;
    }
}
