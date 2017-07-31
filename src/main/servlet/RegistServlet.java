package servlet;

import com.pramy.model.User;
import com.pramy.model.UserRole;

import com.pramy.service.UserServiceImp;
import com.pramy.service.User_RoleServiceImp;
import com.pramy.util.JudgeUtil;
import com.pramy.util.MailUtil;
import com.pramy.util.OutputUtil;
import com.pramy.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class RegistServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void init() {

		threadLocal.set(userService);
		threadLocal1.set(user_roleService);
	}
	private UserService userService;
	private User_RoleService user_roleService;
	private static ThreadLocal<UserService> threadLocal = new ThreadLocal<>();
	private static ThreadLocal<User_RoleService> threadLocal1=new ThreadLocal<>();



	protected void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		String randomCode = (String) session.getAttribute("VerificationCode");
		PrintWriter out = response.getWriter();
		
		  if(!code.equalsIgnoreCase(randomCode)){ 
			  OutputUtil.jsWarning(out,"验证码错误！");
			  return; 
		 }
		 
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String sex = request.getParameter("sex");
		Date creatTime = new Date();
		Date lastLoginTime = new Date();
		Integer level = 1;
		String isLimit = "Yes";

		User user = new User();
		UserService us = threadLocal.get();
		user.setUserName(userName);
		if (!JudgeUtil.isUserName(userName)) {
			OutputUtil.jsWarning(out, "帐号格式为6~16位数字加字母组合！");
			return;
		}
		if (!JudgeUtil.isPassword(password)) {
			OutputUtil.jsWarning(out, "密码为6~16位数字加字母加标点组合！");
			return;
		}
		if (!JudgeUtil.isEmail(email)) {
			OutputUtil.jsWarning(out, "请输入有效邮箱！");
			return;
		}

		if (us.isExit(user)) {
			OutputUtil.jsWarning(out, "账户已存在！");
			return;
		}
		
		password = StringUtil.EncoderByMd5(password);
		user = new User(0, userName, password, email, sex, question, answer, creatTime, lastLoginTime, isLimit, level,
				0);
		session.setAttribute("temepUser", user);

		String mailCode = getVerifyCode();
		session.setAttribute("mailCode", mailCode);
		String content = "用户：" + user.getUserName() + "你好！你的验证码为：" + mailCode;

		MailUtil.sendMail(user.getEmail(),user.getUserName(),content);

		request.getRequestDispatcher("verify.jsp").forward(request, response);


	}

	public void verify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String verifyCode=request.getParameter("verifyCode");
		String mailCode = (String)session.getAttribute("mailCode");
		User user=(User) session.getAttribute("temepUser");
		UserService us = threadLocal.get();
		User_RoleService usr=threadLocal1.get();
		
		String message=null;
		if(StringUtil.isEmpty(mailCode) || StringUtil.isEmpty(mailCode) ||StringUtil.isEmpty(user)){
			message="注册失败";
		}else{
			
			if(!mailCode.equals(verifyCode)){
				message="注册失败";
				
			}else{
				UserRole user_Role=new UserRole();
				user_Role.setRoleId(1);
				user_Role.setUserId(us.add(user));
				if(usr.add(user_Role)>0){
					message="注册成功，请等待管理员审核";
				}
			}
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("verifyResult.jsp").forward(request, response);
		
	}

	private String getVerifyCode() {
		StringBuffer code = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			code.append(random.nextInt(10));
		}
		return code.toString();
	}

}
