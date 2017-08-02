package com.pramy.service.lmp;

import com.pramy.common.SpringContextHolder;
import com.pramy.dao.MyFileMapper;
import com.pramy.dao.SectionMapper;
import com.pramy.model.MyFile;
import com.pramy.model.Section;
import com.pramy.model.User;
import com.pramy.model.UserRole;
import com.pramy.service.SectionService;
import com.pramy.service.UserService;
import com.pramy.service.User_RoleService;
import com.pramy.util.PageUtil;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.xml.ws.soap.Addressing;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/8/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring.xml","classpath:spring/springmvc.xml"})
public class User_RoleServiceImpTest {

    @Autowired
    private User_RoleService urs;
    @Autowired
    private UserService us;
    @Autowired
    private SectionService sectionService;
    @Autowired
    private SectionMapper sectionMapper;
    @Autowired
    private MyFileMapper myFileMapper;


    @Test
    public void post(){
        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.add("userName", "tom");
        form.add("password", "123456");
        form.add("age", "45");
        restTemplate.postForLocation("http://localhost:8080/section/goAdd.do",form);
    }



    @Test
    public void selectByUserId() throws Exception {
        UserRole userRole = new UserRole();
        userRole.setUserId(38);
        System.out.println(urs.add(userRole));
    }

    @Test
    public void add()throws Exception{
        User user = new User();
        user.setUserName("ccl");
        us.add(user);
        Section section = new Section();
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageSize(0);
        System.out.println(sectionService.selectList(section,pageUtil));

    }
    @Test
    public void myfile()throws Exception{
        MyFileMapper mp= (MyFileMapper) SpringContextHolder.getBean("myFileMapper");

        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
        Date time=now.getTime();
        List<MyFile> list=mp.automaticDeleteList(time);
        for (MyFile my :
                list) {
            System.out.println(my);
        }
    }
    @Test
    public void usese(){
        UserService us= (UserService) SpringContextHolder.getBean("userServiceImp");
        System.out.println(us);
    }
}