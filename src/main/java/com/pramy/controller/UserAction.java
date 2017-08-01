package com.pramy.controller;

import com.pramy.common.CommonResult;
import com.pramy.model.User;
import static com.pramy.util.CommonUtil.*;
import static com.pramy.util.JudgeUtil.*;

import com.pramy.model.UserRole;
import com.pramy.service.UserService;
import com.pramy.service.User_RoleService;
import com.pramy.util.MailUtil;
import com.pramy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Random;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/8/1.
 */
@RequestMapping("/user")
@Controller
public class UserAction {
    @Autowired
    private UserService us;
    @Autowired
    private User_RoleService urs;

    @ResponseBody
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public CommonResult<User> register(User user, @RequestParam("code")String code, HttpSession session){
        String randomCode = (String) session.getAttribute("VerificationCode");
        System.out.println(user);
        if(!code.equalsIgnoreCase(randomCode)){
            return getCommon(false,"验证码错误");
        }
        if (!isUserName(user.getUserName())) {
            return getCommon(false,"帐号格式为6~16位数字加字母组合！");
        }
        if (!isPassword(user.getUserPassword())) {

            return getCommon(false,"密码为6~16位数字加字母加标点组合！");
        }
        if (!isEmail(user.getEmail())) {
            return getCommon(false,"请输入有效邮箱！");
        }
        if (us.isExit(user)) {
            return getCommon(false,"账户已存在！");
        }
        user.setUserPassword(StringUtil.EncoderByMd5(user.getUserPassword()));
        user.setCreatTime(new Date());
        user.setLastLoginTime(new Date());
        user.setLevel(1);
        user.setIsLimit("Yes");
        user.setExperience(0);
        session.setAttribute("temepUser",user);

        String mailCode = getVerifyCode();
        session.setAttribute("mailCode", mailCode);
        String content = "用户：" + user.getUserName() + "你好！你的验证码为：" + mailCode;

        MailUtil.sendMail(user.getEmail(),user.getUserName(),content);
        return getCommon(true,"请输入你收到的验证码","/user/verify");
    }

    @RequestMapping("/verify")
    public ModelAndView verify(HttpSession session,ModelAndView model){
        User temep = (User) session.getAttribute("temepUser");
        if (StringUtil.isEmpty(temep)){
            model.setViewName("index");
        }else{
            model.setViewName("verify");
        }
        return model;
    }

    @RequestMapping(value = "/finish")
    public ModelAndView finish(HttpSession session,@RequestParam("verifyCode")String verifyCode,ModelAndView model){
        User user=(User) session.getAttribute("temepUser");
        String mailCode = (String)session.getAttribute("mailCode");
        String message=null;

        if(StringUtil.isEmpty(mailCode) || StringUtil.isEmpty(mailCode) ||StringUtil.isEmpty(user)){
            message="注册失败";
        }else{

            if(!mailCode.equals(verifyCode)){
                message="注册失败";

            }else{
                UserRole user_Role=new UserRole();
                user_Role.setRoleId(1);
                us.add(user);
                user_Role.setUserId(user.getId());
                if(urs.add(user_Role)>0){
                    message="注册成功，请等待管理员审核";
                }
            }
        }
        model.addObject("message",message);
        model.setViewName("verifyResult");
        return model;
    }

    private String getVerifyCode() {
        StringBuffer code = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
