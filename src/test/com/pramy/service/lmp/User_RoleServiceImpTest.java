package com.pramy.service.lmp;

import com.pramy.model.User;
import com.pramy.model.UserRole;
import com.pramy.service.UserService;
import com.pramy.service.User_RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.ws.soap.Addressing;

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
        System.out.println(user.getId());
    }
}