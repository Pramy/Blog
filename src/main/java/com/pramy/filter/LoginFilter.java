package com.pramy.filter;

import com.pramy.common.SpringContextHolder;
import com.pramy.dao.ChatDateMapper;
import com.pramy.dao.MyFileMapper;
import com.pramy.model.MyFile;

import com.pramy.model.User;
import com.pramy.util.DayUtil;

import java.io.File;
import java.io.IOException;
 import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static com.pramy.util.StringUtil.*;



/**
 * Servlet Filter implementation class OverAllFilter
 */
public class LoginFilter implements Filter {

	private String url;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession();
		String path =req.getRequestURI();
		System.out.println(path);
		User user = (User) session.getAttribute("user");

		if (isEmpty(user) && !isUrl(path)){
			req.getRequestDispatcher("/index.jsp").forward(req,resp);
		}else {
			chain.doFilter(req,resp);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		this.url=fConfig.getInitParameter("url");
		System.out.println("自动删除过期信息已启动");
		String path=fConfig.getServletContext().getRealPath("/");
		ClearChatDate ccd = new ClearChatDate(7, 1L);
		ClearFile cF = new ClearFile(path, 14, 1L);
		Thread t1 = new Thread(cF);
		Thread t2 = new Thread(ccd);
		t1.start();
		t2.start();
		
	}

	private boolean isUrl(String path){
		List<String> list = Arrays.asList(this.url.split(","));
		for (String url :
				list) {
			if (path.contains(url)){
				return true;
			}
		}
		return false;
	}
	@Override
	public void destroy() {
		
	}

}

class ClearFile implements Runnable{

	private String path;   //路径
	private Date time;  //时间间隔 天
	private Long sleepTime; //睡眠时间 天
	private MyFileMapper myFileMapper;
	ClearFile(String path, Integer day, Long sleepTime) {
		this.path=path;
		this.sleepTime=sleepTime*1000*60*60*24;
		this.time=DayUtil.getBeforeDate(day);
		this.myFileMapper= (MyFileMapper) SpringContextHolder.getBean("myFileMapper");
	}

	@Override
	public void run() {

		while(true){
			System.out.println("执行删除过期文件");
			System.out.println("时间间隔："+this.time+"天, 睡眠时间："+(this.sleepTime/86400000)+"天");
			List<MyFile> list=myFileMapper.automaticDeleteList(this.time);
			for (MyFile myFile : list) {
				File file = new File(this.path+myFile.getFileUrl()+myFile.getFileName());
				if(file.exists() && file.delete()){
					System.out.println("删除文件:"+file.getName()+"成功");
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
	private ChatDateMapper chatDateMapper;

	ClearChatDate(Integer day, Long sleepTime) {

		this.time= DayUtil.getBeforeDate(day);
		this.sleepTime=sleepTime*1000*60*60*24;
		this.chatDateMapper = (ChatDateMapper) SpringContextHolder.getBean("chatDateMapper");
	}
	
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