package com.ssmsim.controller;

import com.ssmsim.service.IAdminService;
import com.ssmsim.utils.ImportUsers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Boolean adminLogin(@RequestParam("userName")String userName, @RequestParam("pwd")String password, HttpSession session){
        boolean state = adminService.adminLogin(userName, password);
        session.setAttribute("adminId", userName);
        return state;
    }

    @RequestMapping(value = "import", method = RequestMethod.GET)
    @ResponseBody
    public Boolean importUsers(HttpServletRequest request) {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext()
        );
        String target = request.getParameter("type");
        boolean state = false;
        if(multipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Iterator iter = multipartHttpServletRequest.getFileNames();
            if (iter.hasNext()) {
                String fileFileName = iter.next().toString();
                MultipartFile file = multipartHttpServletRequest.getFile(fileFileName);
                if (file != null) {
                    fileFileName = file.getOriginalFilename();
                    long currentTime = System.currentTimeMillis();
                    // 命名为 '当前时间(s) + 文件名'
                    String myFileName = currentTime + "_" + fileFileName;
                    try {
                        // 将文件存储到本地
                        file.transferTo(new File("/home/brian/IdeaProjects/ssmsim/files/" + myFileName));

                        state = ImportUsers.importUsers(target, myFileName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return state;
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public Boolean adminLogout(HttpSession session){
        session.removeAttribute("adminId");
        return true;
    }
}
