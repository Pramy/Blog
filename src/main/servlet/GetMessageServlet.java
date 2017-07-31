package servlet;


import com.pramy.model.ChatDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

/**
 * Servlet implementation class GetMessageServlet
 */
public class GetMessageServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;

    public static Hashtable<String, Hashtable<String, ChatDate>> waitMap = new Hashtable<String, Hashtable<String, ChatDate>>();

    protected void get(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ChatDate chatDate = new ChatDate();
        String id = request.getParameter("sectionId");
        Hashtable<String, ChatDate> sectionMap = null;
        if (waitMap.get(id) == null) {
            sectionMap = new Hashtable<String, ChatDate>();
            waitMap.put(id, sectionMap);
        } else {
            sectionMap = waitMap.get(id);
        }
        sectionMap.put(request.getSession().getId(), chatDate);
        synchronized (chatDate) {
            try {
                chatDate.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        PrintWriter out = response.getWriter();
        String head = SDF.format(chatDate.getTime()) + "\r\n";
        out.print(head + chatDate.getUserName() + "说:" + chatDate.getContent());
        out.flush();
        out.close();
    }

}
