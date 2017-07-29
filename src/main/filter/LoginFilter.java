package com.pramy.filter;

import com.pramy.dao.ChatDateMapper;
import com.pramy.dao.MyFileMapper;
import com.pramy.model.MyFile;
import com.pramy.util.DbUtil;
import com.pramy.util.StringUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
 import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 * Servlet Filter implementation class OverAllFilter
 */
public class LoginFilter implements Filter {

	private String url;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//获取当前路径
		String requestUrl = req.getRequestURI();
	   //获取相对路径
//		System.out.println(requestUrl);
//		System.out.println(req.getContextPath());
//		System.out.println(req.getRequestURI());
		requestUrl = requestUrl.substring(req.getContextPath().length()+1);
//		System.out.println(requestUrl);
		List<String> list = Arrays.asList(this.url.split(","));


/*		if (requestUrl.equals("index.jsp") || requestUrl.equals("regist.jsp") || requestUrl.equals("updatePassword.jsp") ||
				requestUrl.equals("findPassword.jsp") || requestUrl.contains("VerificationCodeServlet")||
				requestUrl.contains("LoginServlet")|| requestUrl.contains("FindPasswordServlet") || requestUrl.contains("RegistServlet")||requestUrl.contains(".jpg")||requestUrl.contains(".css")
				|| requestUrl.contains(".js")) {
			chain.doFilter(request, response);
			return;
		}*/
		HttpSession session = req.getSession();
//		System.out.println(session.getAttribute("user"));

/*		if ( StringUtil.isEmpty(session.getAttribute("user"))
				|| StringUtil.isEmpty(session.getAttribute("role"))
				|| StringUtil.isEmpty(session.getAttribute("power"))) {

			for (String string : list) {
				if(string.equals(requestUrl)||requestUrl.endsWith(string)){
					chain.doFilter(request, response);
					return;
				}
			}
			StringBuffer path=new StringBuffer();
			//如果有一个/的话要添加多一个点才可以退回根目录
			while(requestUrl.contains("/")){
				path.append("../");
				requestUrl=requestUrl.replaceFirst("/", "");
			}
			path.append("index.jsp");
			resp.sendRedirect(path.toString());
			return;
		}*/
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		ServletContext servletContext = fConfig.getServletContext();
		this.url=fConfig.getInitParameter("Url");
		System.out.println("自动删除过期信息已启动");
		String path=fConfig.getServletContext().getRealPath("/");
		ClearChatDate ccd = new ClearChatDate(7, 1L);
		ClearFile cF = new ClearFile(path, 14, 1L);
		Thread t1 = new Thread(cF);
		Thread t2 = new Thread(ccd);
		t1.start();
		t2.start();
		
	}

	@Override
	public void destroy() {
		
	}

}

class ClearFile implements Runnable{

	private String path;   //路径
	private Date time;  //时间间隔 天
	private Long sleepTime; //睡眠时间 天
	public ClearFile(String path,Integer day,Long sleepTime) {
		this.path=path;
		this.sleepTime=sleepTime*1000*60*60*24;
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		this.time=now.getTime();
	}

		@Autowired
		MyFileMapper myFileMapper;


	@Override
	public void run() {



		while(true){
			System.out.println("执行删除过期文件");
			System.out.println("时间间隔："+this.time+"天, 睡眠时间："+(this.sleepTime/86400000)+"天");
			List<MyFile> list=myFileMapper.automaticDeleteList(this.time);
			for (MyFile myFile : list) {
				File file = new File(this.path+myFile.getFileUrl()+myFile.getFileName());
				if(file.exists()){
					file.delete();
				}
			}
			myFileMapper.automaticDelete(this.time);
			try {
				System.out.println("开始休眠");
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
class ClearChatDate implements Runnable{


	private Date time;
	private Long sleepTime;
	public ClearChatDate(Integer day,Long sleepTime) {

		Calendar now = Calendar.getInstance();
		now.setTime(new Date());
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		this.time=now.getTime();
		this.sleepTime=sleepTime*1000*60*60*24;
	}
	
		@Autowired
		ChatDateMapper chatDateMapper;
	@Override
	public void run() {


		while(true){
			System.out.println("执行删除聊天记录操作");
			System.out.println("时间间隔："+this.time+"天, 睡眠时间："+(this.sleepTime/86400000)+"天");
			chatDateMapper.automaticDelete(time);
			try {
				System.out.println("开始休眠");
				Thread.sleep(this.sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}