package servlet;

import com.pramy.model.*;
import com.pramy.service.*;
import com.pramy.util.OutputUtil;
import com.pramy.util.PageUtil;
import com.pramy.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;


    private UserService us;
    private User_RoleService ur;
    private RoleService rs;
    private Role_PowerService rp;
    private PowerService ps;

    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ServletContext appliction = getServletContext();
        Map<String, HttpSession> map = null;
        if (null == appliction.getAttribute("onlineUserMap")) {
            map = new HashMap<>();
            appliction.setAttribute("onlineUserMap", map);
        } else {
            map = (Map<String, HttpSession>) appliction.getAttribute("onlineUserMap");
        }

        String code = request.getParameter("code");
        String randomCode = (String) session.getAttribute("VerificationCode");
        String userName = request.getParameter("username");
        String password = StringUtil.EncoderByMd5(request.getParameter("password"));

        PrintWriter out = response.getWriter();

        if (!code.equalsIgnoreCase(randomCode)) {
            System.out.println("你输入的验证码：" + code);
            System.out.println("后台验证码：" + randomCode);
            request.setAttribute("message", "验证码有误，请重新输入");
            request.getRequestDispatcher("verifyResult.jsp").forward(request, response);
            return;
        }

        User user = new User();
        UserRole user_Role = new UserRole();
        Role role = new Role();
        RolePower role_Power = new RolePower();
        Power power = new Power();

        user.setUserName(userName);
        user.setUserPassword(password);
        user = us.selectOne(user);
        if (StringUtil.isEmpty(user.getUserName()) || !user.getUserPassword().equals(password)) {
            OutputUtil.jsWarning(out, "帐号或密码有误");
            return;
        }
        if (user.getIsLimit().equals("Yes")) {
            OutputUtil.jsWarning(out, "你没有权限登录。请耐心等待审核");
            return;
        }
        user_Role.setUserId(user.getId());
        user_Role = ur.selectOne(user_Role);

        role = rs.selectOne(user_Role.getRoleId());

        role_Power = rp.selectOne(role.getId());


        power.setId(role_Power.getPowerId());
        PageUtil pageUtil = new PageUtil();
        pageUtil.setPageSize(0);
        List<Power> Powerlist = ps.selectList(power,pageUtil);


        user.setLastLoginTime(new Date());

        session.setAttribute("user", user);
        session.setAttribute("role", role);
        session.setAttribute("power", Powerlist);

        //如果有人登录了就销毁对方的session
        try {
            if (map.get(user.getUserName()) == null) {
                map.put(user.getUserName(), session);
                appliction.setAttribute("onlineUserMap", map);
            } else {
                HttpSession session2 = map.get(user.getUserName());
                if (!session2.getId().equals(session.getId())) {
                    map.put(user.getUserName(), session);
                    appliction.setAttribute("onlineUserMap", map);
                    session2.invalidate();
                }
            }
        } catch (Exception e) {

        } finally {
            response.sendRedirect("section/SectionServlet");
        }
    }

}
