package com.pramy.controller;

import com.pramy.common.CommonResult;
import com.pramy.model.User;
import com.pramy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.pramy.util.CommonUtil.*;
import static com.pramy.util.StringUtil.*;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/8/1.
 */

@Controller
@RequestMapping("/findPassword")
public class FindPasswordAction {

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/get",method = RequestMethod.POST)
    public CommonResult<User> get(@RequestParam("userName") String userName, HttpSession session){
        System.out.println("yes");
        if(isEmpty(userName)){
            return getCommon(false,"用户名不能为空");
        }
        User user = new User();
        user.setUserName(userName);
        user=userService.selectOneByName(user);
        if(isEmpty(user)){
            return getCommon(false,"用户不存在");
        }
        session.setAttribute("findUser", user);
        return getCommon(true,"","/findPassword/goUpdate");
    }

    @RequestMapping(value = "goUpdate")
    public ModelAndView goUpdate(ModelAndView modelAndView){
        modelAndView.setViewName("updatePassword");
        return modelAndView;
    }
    @ResponseBody
    @RequestMapping(value = "update",method = RequestMethod.POST)
    public CommonResult<User> update(User user ,@RequestParam("rePassword")String rePassword,HttpSession session){
        User findUser = (User) session.getAttribute("findUser");

        if (isEmpty(rePassword)|| isEmpty(user.getUserPassword()) ||isEmpty(user.getEmail())
                || isEmpty(user.getAnswer()) || isEmpty(user.getQuestion())){
            return  getCommon(false,"所填内容不能为空");
        }

        if (!rePassword.equals(user.getUserPassword())){
            return getCommon(false,"两次密码不同");
        }
        if(!findUser.getEmail().equals(user.getEmail()) || !findUser.getAnswer().equals(user.getAnswer())){
            return getCommon(false,"修改失败，信息有误");
        }

        findUser.setUserPassword(EncoderByMd5(user.getUserPassword()));
        if(userService.update(findUser)>0){
            return getCommon(true,"修改成功，请重新登录","/index.jsp");
        }
        return getCommon(false,"修改失败");
    }

    @RequestMapping("/fuck")
    public void fuck(){
        System.out.println("fuck");
    }
}
