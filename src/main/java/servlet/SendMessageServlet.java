package servlet;

import com.pramy.model.ChatDate;
import com.pramy.model.User;
import com.pramy.service.ChatDateService;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class SendMessageServlet
 */
public class SendMessageServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ChatDateService cs;
       
	protected void send(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chatMesaage = request.getParameter("chatmsg");
		String sectionId = request.getParameter("sectionId");
		Integer id = Integer.parseInt(sectionId);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		//发一条消息获得1经验  够1000经验就升一级
		Integer experience=user.getExperience();
		Integer level = user.getLevel();
		experience=experience+1;
		if(experience>=1000){
			level=level+1;
			experience=0;
		}
		user.setExperience(experience);
		user.setLevel(level);
		session.setAttribute("user", user);

		Set<String> sessions = GetMessageServlet.waitMap.get(sectionId).keySet();
		Iterator<String> sessionsIt = sessions.iterator();
		
		Date date = new Date();
		String userName = user.getUserName();
		while (sessionsIt.hasNext()) {
			String sessionId = sessionsIt.next();
			ChatDate chatDate = GetMessageServlet.waitMap.get(sectionId).get(sessionId);
			chatDate.setContent(chatMesaage);
			chatDate.setUserName(userName);
			chatDate.setTime(date);
			chatDate.setSectionId(id);
			sessionsIt.remove();
			synchronized (chatDate) {
				chatDate.notifyAll();
			}
		}
		ChatDate chatDate = new ChatDate(null, date, chatMesaage, id, userName);
		cs.add(chatDate);
	}

}
