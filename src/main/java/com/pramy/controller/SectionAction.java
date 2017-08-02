package com.pramy.controller;

import com.pramy.common.CommonResult;
import com.pramy.model.Role;
import com.pramy.model.Section;
import com.pramy.model.User;
import com.pramy.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;

import static com.pramy.util.StringUtil.*;
import static com.pramy.util.CommonUtil.*;
/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/8/2.
 */
@RequestMapping("/section")
@Controller
public class SectionAction {

    @Autowired
    private SectionService sectionService;

    @RequestMapping(value = "/goAdd.do")
    public ModelAndView add(HttpSession session, ModelAndView model){
        return toGo(session,"/section/sectionAdd");
    }

    @RequestMapping(value = "/goSelect.do")
    public ModelAndView goSelect(HttpSession session){

        return toGo(session,"/section/sectionSelect");
    }
    @ResponseBody
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    public CommonResult add(HttpSession session, Section section){
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("role");
        if(isEmpty(section)){
            return getCommon(false,"请填写正确信息");
        }
        if(!role.getRoleName().equals("超级版主")){
            return getCommon(false,"操作失败");
        }
        section.setCreatTime(new Date());
        section.setCreaterName(user.getUserName());
        if(sectionService.add(section)>0){
            return getCommon(true,"添加成功","/welcome.do");
        }else{
            return getCommon(false,"添加失败");
        }
    }

    private ModelAndView toGo(HttpSession session,String path){
        ModelAndView model = new ModelAndView();
        User user = (User) session.getAttribute("user");
        Role role = (Role) session.getAttribute("role");
        if(isEmpty(user)||isEmpty(role)){
            model.setViewName("index");
        }else if(role.getRoleName().equals("超级版主")){
            model.setViewName(path);
        }else{
            throw new RuntimeException();
        }
        return model;
    }
}
