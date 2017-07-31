package servlet;

import com.pramy.model.Role;
import com.pramy.model.User;
import com.pramy.service.UserServiceImp;
import com.pramy.util.JudgeUtil;
import com.pramy.util.OutputUtil;
import com.pramy.util.PageUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet implementation class UserServlet
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService us;

	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String sex = request.getParameter("sex");
		String isLimit = request.getParameter("isLimit");
		Integer level = Integer.parseInt(request.getParameter("level"));
		PrintWriter out = response.getWriter();
		if(!JudgeUtil.isEmail(email)){
			OutputUtil.jsWarning(out, "请输入有效邮箱！");
	         return;
		}
		User user = new User();
		user.setUserName(userName);
		user=us.selectOne(user);
		user.setSex(sex);
		user.setEmail(email);
		user.setIsLimit(isLimit);
		user.setLevel(level);
		user.setQuestion(question);
		user.setAnswer(answer);

		HttpSession session = request.getSession();
		if(us.update(user)>0){
			System.out.println("修改成功");
			session.setAttribute("user",user);
			String str ="<script>alert('修改成功');</script> ";
			out.write(str);
			out.flush();
			String str1="<script>window.location.href='../user/UserServlet?action=select';</script> ";
			out.write(str1);
			out.flush();
			out.close();
			return;
		}else{
			OutputUtil.jsWarning(out, "修改失败");
			return;
		}
		
	}
	protected void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		HttpSession session =request.getSession();
		Role Role =(Role) session.getAttribute("role");
		if(Role.getRoleName().equals("普通用户")){
			request.getRequestDispatcher("/user/userUpdate.jsp").forward(request,response);
			return;
		}
		String pageSize=request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String select =  request.getParameter("select");
		String limit = request.getParameter("isLimit");
		user.setUserName(select);
		user.setIsLimit(limit);
		Integer total = us.total(user);
		
		PageUtil pageUtil = new PageUtil(pageSize, pageNo, total);
		//设置页数大小
		request.setAttribute("pageSize", pageUtil.getPageSize());
		//当前页数
		request.setAttribute("pageNo", pageUtil.getPageNo());
		//总页数
		request.setAttribute("totalPage",pageUtil.getTotalPage());

		List<User> list = us.selectList(user,pageUtil);

		for (User u: list
			 ) {
			u.setCreatTime(new Timestamp(u.getCreatTime().getTime()));
			u.setLastLoginTime(new Timestamp(u.getCreatTime().getTime()));
		}

		//查询条件
		request.setAttribute("isLimit", limit);
		request.setAttribute("select", select);
		request.setAttribute("UserList", list);
		request.getRequestDispatcher("userSelect.jsp").forward(request, response);
	}
	
	protected void selectOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		User user = new User();
		user.setUserName(userName);
		user=us.selectOne(user);
		request.setAttribute("user", user);
		request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession();
		String userName = request.getParameter("userName");
		Role role = (Role)session.getAttribute("role");
		if(role==null || role.getRoleName().equals("普通用户")){
			return;
		}
		User user = new User();
		user.setUserName(userName);
		us.delete(user);
		select(request, response);
	}
}
