package com.pramy.controller;



import com.pramy.model.ChatDate;

import com.pramy.service.ChatDateService;
import com.pramy.util.OutputUtil;
import com.pramy.util.PageUtil;
import com.pramy.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ChatLogServlet
 */

public class ChatDateServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ChatDateService cs;


	protected void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChatDate chatDate = new ChatDate();
		String pageSize=request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String select =  request.getParameter("select");
		String sectionId = request.getParameter("sectionId");
		if(!StringUtil.isEmpty(sectionId)){
			chatDate.setSectionId(Integer.parseInt(sectionId));
		}else{
			OutputUtil.jsWarning(response.getWriter(), "查看失败");
			return;
		}
		
		if (!StringUtil.isEmpty(select)){
			chatDate.setUserName(select);
		}

		Integer total = cs.total(chatDate);
		
		PageUtil pageUtil = new PageUtil(pageSize, pageNo, total);
		//设置页数大小
		request.setAttribute("pageSize", pageUtil.getPageSize());
		//当前页数
		request.setAttribute("pageNo", pageUtil.getPageNo());
		//总页数
		request.setAttribute("totalPage",pageUtil.getTotalPage());

		List<ChatDate> list = cs.selectList(chatDate,pageUtil);
		for (ChatDate chatDate2 : list) {
			System.out.println(chatDate2);
		}
		//查询条件
		request.setAttribute("select", select);
		request.setAttribute("sectionId", sectionId);
		request.setAttribute("ChatDateList", list);
		request.getRequestDispatcher("chatdate.jsp").forward(request, response);
	}
}
