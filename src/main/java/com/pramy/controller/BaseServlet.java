package com.pramy.controller;

import com.pramy.util.StringUtil;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

@Controller
public class BaseServlet extends HttpServlet {
    /**
     * 将请求方法到不同的servlet中的不同方法
     */
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        String action = request.getParameter("action");
        Class<? extends BaseServlet> clazz = this.getClass();
        try {
            if (StringUtil.isEmpty(action)) {
                action = "myMethod";
            }
            Method method = clazz.getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, request, response);
        } catch (Exception e) {
            System.out.println("你的方法：" + action + "不存在");
            e.printStackTrace();
        }
    }
}