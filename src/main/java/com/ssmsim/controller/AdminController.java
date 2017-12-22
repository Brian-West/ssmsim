package com.ssmsim.controller;

import com.ssmsim.model.MasterRequest;
import com.ssmsim.service.IAdminService;
import com.ssmsim.utils.ImportUsers;
import com.ssmsim.viewObject.LoginUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin")
@CrossOrigin("http://localhost:8081")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public Map adminLogin(@RequestBody LoginUser user, HttpSession session) {
        String state = adminService.adminLogin(user.getUserName(), user.getPassword());
        if(state.equals("success")) {
            session.setAttribute("adminId", user.getUserName());
        }
        Map<String, Object> result = new HashMap<>();
        result.put("valid", state);
        return result;
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

    @RequestMapping(value = "requests", method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getAllRequests() {
        return adminService.getRequests();
    }

    @RequestMapping(value = "request", method = RequestMethod.GET)
    @ResponseBody
    public MasterRequest getRequest(@RequestParam("requestId")String id, HttpSession session) {
        int requestId = Integer.parseInt(id);
        session.setAttribute("requestId", requestId);
        return adminService.getRequestById(requestId);
    }

    @RequestMapping(value = "admit", method = RequestMethod.GET)
    @ResponseBody
    public Boolean admitRequest(HttpSession session) {
        int requestId = (int)session.getAttribute("requestId");
        adminService.acceptRequest(requestId);
        return true;
    }

    @RequestMapping(value = "refuse", method = RequestMethod.GET)
    public Boolean refuseRequest(HttpSession session, @RequestParam("reason")String reason) {
        int requestId = (int)session.getAttribute("requestId");
        adminService.refuseRequest(requestId, reason);
        return true;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    @ResponseBody
    public Boolean adminLogout(HttpSession session){
        session.removeAttribute("adminId");
        return true;
    }
}
