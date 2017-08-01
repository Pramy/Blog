package com.pramy.controller;

import com.pramy.common.CommonResult;
import com.pramy.model.*;
import com.pramy.service.*;

import com.pramy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.pramy.util.CommonUtil.getCommon;

/**
 * IntelliJ IDEA 17
 * Created by Administrator on 2017/7/30.
 */

@Controller
public class LoginAction {
    @Autowired
    private UserService us;
    @Autowired
    private User_RoleService ur;
    @Autowired
    private RoleService rs;
    @Autowired
    private Role_PowerService rp;
    @Autowired
    private PowerService ps;

    @ResponseBody
    @RequestMapping("/login")
    public CommonResult<User> login(User user, @RequestParam("code") String code, HttpServletRequest request){
        HttpSession session = request.getSession();

        ServletContext appliction = request.getServletContext();
        Map<String, HttpSession> map = null;
        if (null == appliction.getAttribute("onlineUserMap")) {
            map = new HashMap<>();
            appliction.setAttribute("onlineUserMap", map);
        } else {
            map = (Map<String, HttpSession>) appliction.getAttribute("onlineUserMap");
        }
        String verificationCode = (String) session.getAttribute("VerificationCode");

        if(StringUtil.isEmpty(code)){
            return getCommon(false,"验证码不能为空");
        }else if(StringUtil.isEmpty(user.getUserName())){
            return getCommon(false,"用户名不能为空");
        }else if(StringUtil.isEmpty(user.getUserPassword())){
            return getCommon(false,"密码不能为空");
        }/*else if(!code.equalsIgnoreCase(verificationCode)){
            return getCommon(false,"验证码错误");
        }*/
        // 加密
        user.setUserPassword(StringUtil.EncoderByMd5(user.getUserPassword()));
        User user1 = us.selectOneByName(user);
        if(StringUtil.isEmpty(user1)){
            return getCommon(false,"帐号不存在");
        }else if(!user.getUserPassword().equalsIgnoreCase(user1.getUserPassword())){
            return getCommon(false,"密码错误");
        }else if(user1.getIsLimit().equals("Yes")){
            return getCommon(false,"你没有登录权限");
        }
        user =user1;
        UserRole user_Role = new UserRole();
        Role role = null;
        RolePower role_Power = new RolePower();
        Power power = new Power();

        user_Role.setUserId(user.getId());
        user_Role = ur.selectByUserId(user_Role);

        role = rs.selectOneById(user_Role.getRoleId());
        role_Power.setRoleId(role.getId());

//        比较复杂先放下
//        role_Power = rp.selectByRoleId(role_Power);


//        power.setId(role_Power.getPowerId());
//        PageUtil pageUtil = new PageUtil();
//        pageUtil.setPageSize(0);
//        List<Power> Powerlist = ps.selectList(power,pageUtil);

        user.setLastLoginTime(new Date());

        session.setAttribute("user", user);
        session.setAttribute("role", role);
//        session.setAttribute("power", Powerlist);

        try {
            if (map.get(user.getUserName()) == null) {
                map.put(user.getUserName(), session);
                appliction.setAttribute("onlineUserMap", map);
            } else {
                HttpSession session2 = map.get(user.getUserName());
                if (!session2.getId().equals(session.getId())) {
                    map.put(user.getUserName(), session);
                    appliction.setAttribute("onlineUserMap", map);
                    session2.invalidate();
                }
            }
        } catch (Exception e) {
            return getCommon(true,"帐号已在线","/welcome");
        }
        return getCommon(true, "登录成功", "/welcome");
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public ModelAndView  welcome(HttpSession session){
        ModelAndView model=new ModelAndView();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("role");
        if(StringUtil.isEmpty(user)||StringUtil.isEmpty(role)){
            model.setViewName("/index");
        }else {
            model.setViewName("/section/section");
        }
        return model;
    }


}
