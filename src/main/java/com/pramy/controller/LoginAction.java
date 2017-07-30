package com.pramy.controller;

import com.pramy.model.User;
import com.pramy.service.lmp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * IntelliJ IDEA 17
 * Created by Administrator on 2017/7/30.
 */

@Controller
public class LoginAction {
    @Autowired
    private UserServiceImp us;
    @Autowired
    private RoleServiceImp rs;
    @Autowired
    private PowerServiceImp ps;
    @Autowired
    private User_RoleServiceImp urs;
    @Autowired
    private Role_PowerServiceImp rps;

    @RequestMapping("/login")
    public String login(User user, @RequestParam("code") String code, HttpServletRequest request){
        HttpSession session = request.getSession();
        ServletContext appliction = request.getServletContext();
        Map<String, HttpSession> map = null;
        if (null == appliction.getAttribute("onlineUserMap")) {
            map = new HashMap<>();
            appliction.setAttribute("onlineUserMap", map);
        } else {
            map = (Map<String, HttpSession>) appliction.getAttribute("onlineUserMap");
        }
        String randomCode = (String) session.getAttribute("VerificationCode");

        return null;
    }

}
