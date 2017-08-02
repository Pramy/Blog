package com.pramy.controller;

import com.pramy.model.Section;
import com.pramy.service.SectionService;
import com.pramy.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/8/2.
 */
@RequestMapping("/section")
@Controller
public class SectionAction {

    @Autowired
    private SectionService sectionService;

    @ResponseBody
    @RequestMapping(value = "/initSection",method = RequestMethod.POST)
    public String init(HttpSession session){
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageSize(0);
        List<Section> list = sectionService.selectList(new Section(),pageUtil);
        for (Section section:list
                ) {
            section.setCreatTime(new Timestamp(section.getCreatTime().getTime()));
        }
        session.setAttribute("sectionList", list);
        return "success";
    }
}
