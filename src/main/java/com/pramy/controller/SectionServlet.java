package com.pramy.controller;

import com.pramy.model.Role;
import com.pramy.model.Section;
import com.pramy.model.User;
import com.pramy.service.SectionServiceImp;
import com.pramy.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class SectionAddServlet
 */
public class SectionServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
	private SectionService ss;

	/**
	 * 添加版块
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sectionName=request.getParameter("sectionName");
		Integer level = Integer.parseInt(request.getParameter("level"));
		HttpSession session = request.getSession();
		Integer id = null;
		Date creatTime = new Date();
		User  user = (User) session.getAttribute("user");
		String createrName= user.getUserName();
		Section section = new Section(id, sectionName, creatTime, level,createrName);


		if(ss.add(section)>0){
			PageUtil pageUtil = new PageUtil();
			pageUtil.setPageSize(0);
			List<Section> list=ss.selectList(new Section(),pageUtil);
			session.setAttribute("sectionList", list);
			response.sendRedirect("section.jsp");
		}else {
			return;
		}
	}
	
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Section section = new Section();
		String pageSize=request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String select =  request.getParameter("select");
		section.setSectionName(select);

		Integer total = ss.total(section);
		
		PageUtil pageUtil = new PageUtil(pageSize, pageNo, total);
		//设置页数大小
		request.setAttribute("pageSize", pageUtil.getPageSize());
		//当前页数
		request.setAttribute("pageNo", pageUtil.getPageNo());
		//总页数
		request.setAttribute("totalPage",pageUtil.getTotalPage());

		List<Section> list = ss.selectList(section,pageUtil);

		for (Section s:list
			 ) {
			s.setCreatTime(new Timestamp(s.getCreatTime().getTime()));
		}

		//查询条件
		request.setAttribute("select", select);
		request.setAttribute("SectionList", list);
		request.getRequestDispatcher("sectionSelect.jsp").forward(request, response);
	}
	
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Integer id =Integer.parseInt(request.getParameter("sectionId"));
		HttpSession session = request.getSession();
		Role role = (Role) session.getAttribute("role");
		if(role==null || role.getRoleName().equals("普通用户")){
			return;
		}
		Section section=new  Section();
		section.setId(id);
		ss.delete(section);
		select(request, response);
	}
	
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		Role role = (Role) session.getAttribute("role");
		if(role==null || role.getRoleName().equals("普通用户")){
			return;
		}
		Integer id = Integer.parseInt(request.getParameter("sectionId"));
		String sectionName=request.getParameter("sectionName");
		String level = request.getParameter("level");
		Section section = (Section) session.getAttribute("sectionUpdate");
		if(section==null){
			section=new Section();
		}
		if(level!=null &&sectionName!=null){
			section.setSectionName(sectionName);
			section.setLevel(Integer.parseInt(level));
		}else{			
			section.setId(id);
		}
		if(sectionName==null){
			section=ss.selectOne(id);
		}else{
			ss.update(section);
		}
		session.setAttribute("sectionUpdate", section);
		response.sendRedirect("sectionUpdate.jsp");
	}
	public void myMethod(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PageUtil pageUtil = new PageUtil();
		pageUtil.setPageSize(0);
		List<Section> list = ss.selectList(new Section(),pageUtil);
		for (Section section:list
				) {
			section.setCreatTime(new Timestamp(section.getCreatTime().getTime()));
		}
		request.setAttribute("sectionList", list);
		request.getRequestDispatcher("section.jsp").forward(request, response);
	}
}
