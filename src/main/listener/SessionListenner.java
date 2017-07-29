package com.pramy.listener;


import com.pramy.model.User;
import com.pramy.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;



/**
 * Application Lifecycle Listener implementation class SessionListenner
 *
 */
public class SessionListenner implements HttpSessionListener {
    @Autowired
    private UserService us;
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	HttpSession session = arg0.getSession();
         System.out.println("创建id："+session.getId());
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         HttpSession session = arg0.getSession();
         User user = (User)session.getAttribute("user");
         if(user==null){
        	 return;
         }
         System.out.println("用户下线，更新状态");
         us.update(user);
    }
	
}
