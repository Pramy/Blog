package com.pramy.controller;


import com.pramy.model.User;
import com.pramy.service.UserServiceImp;
import com.pramy.util.OutputUtil;
import com.pramy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class FindPassword
 */
public class FindPasswordServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;



	private UserService us;


	public void check(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		request.setAttribute("userName", userName);
		User user = new User();
		user.setUserName(userName);
		user = us.selectOne(user);
		if(StringUtil.isEmpty(user.getUserName())){
			PrintWriter out =response.getWriter();
			OutputUtil.jsWarning(out, "帐号不存在");
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("findUser", user);
		request.getRequestDispatcher("updatePassword.jsp").forward(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");  
		String answer = request.getParameter("answer");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("findUser");

		PrintWriter out = response.getWriter();
		if(!password.equals(repassword)){
			OutputUtil.jsWarning(out, "两次密码不同");
			return ;
		}
		if(!email.equals(user.getEmail()) || !answer.equals(user.getAnswer())){
			OutputUtil.jsWarning(out, "修改失败，信息有误");
			return;
		}
		user.setUserPassword(StringUtil.EncoderByMd5(password));
		if(us.update(user)>0){
			String str ="<script>alert('修改成功，请重新登录');</script> ";
			out.write(str);
			out.flush();
			String str1="<script>window.location.href='index.jsp';</script> ";
			out.write(str1);
			out.flush();
			out.close();
			return;
		}else{
			OutputUtil.jsWarning(out, "修改失败");
			return ;
		}
 	}
}
