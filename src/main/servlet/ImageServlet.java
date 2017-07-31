package servlet;

import com.pramy.model.MyFile;

import com.pramy.service.MyFileServiceImp;
import com.pramy.util.PageUtil;
import com.pramy.util.StringUtil;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ImageServlet
 */
public class ImageServlet extends BaseServlet {
    private static final long serialVersionUID = 1L;


    private MyFileService ms;

    public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MyFile myFile = new MyFile();
        myFile.setFileType("image");
        String pageSize = request.getParameter("pageSize");
        String pageNo = request.getParameter("pageNo");
        String select = request.getParameter("select");
        if (StringUtil.isEmpty(pageSize)) {
            pageSize = "12";
        }
        if (!StringUtil.isEmpty(select)) {
            myFile.setFileName(select);
        }
        Integer total = ms.total(myFile);

        PageUtil pageUtil = new PageUtil(pageSize, pageNo, total);

        //设置页数大小
        request.setAttribute("pageSize", pageUtil.getPageSize());
        //当前页数
        request.setAttribute("pageNo", pageUtil.getPageNo());
        //总页数
        request.setAttribute("totalPage", pageUtil.getTotalPage());


        List<MyFile> list = ms.selectList(myFile, pageUtil);
        request.setAttribute("imageList", list);
        request.setAttribute("select", select);
        request.getRequestDispatcher("imageshare.jsp").forward(request, response);
    }
}
