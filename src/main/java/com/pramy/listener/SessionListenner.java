package com.pramy.listener;


import com.pramy.common.SpringContextHolder;
import com.pramy.model.User;
import com.pramy.service.UserService;
import com.pramy.util.StringUtil;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



/**
 * Application Lifecycle Listener implementation class SessionListenner
 *
 */
public class SessionListenner implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent arg0)  {

    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         HttpSession session = arg0.getSession();
         User user = (User)session.getAttribute("user");
         if(StringUtil.isEmpty(user)){
             return;
         }
        UserService us= (UserService) SpringContextHolder.getBean("userServiceImp");
         System.out.println("用户："+user.getUserName()+"下线，更新状态");
         us.update(user);
    }
	
}
