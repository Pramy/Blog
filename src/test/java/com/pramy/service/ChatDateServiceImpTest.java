package com.pramy.service;

import com.pramy.dao.ChatDateMapper;
import com.pramy.dao.RolePowerMapper;
import com.pramy.dao.UserRoleMapper;
import com.pramy.model.ChatDate;
import com.pramy.model.RolePower;
import com.pramy.model.User;
import com.pramy.model.UserRole;
import com.pramy.service.lmp.ChatDateServiceImp;
import com.pramy.util.PageUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


/**
 * IntelliJ IDEA 17
 * Created by Pramy on 2017/7/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring.xml")
public class ChatDateServiceImpTest {
    @Autowired
    private RolePowerMapper ur;
    @Test
    public void total() throws Exception {


        PageUtil pageUtil = new PageUtil("12","1",10);
        User user=new User();
        user.setUserName("admin");
        UserRole userRole=new UserRole();
        RolePower rolePower = new RolePower();
        rolePower.setRoleId(3);
        System.out.println(ur.selectByRoleId(rolePower));

    }

}