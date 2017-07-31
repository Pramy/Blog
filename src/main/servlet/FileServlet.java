package servlet;



import com.pramy.util.JudgeUtil;
import com.pramy.util.OutputUtil;
import com.pramy.util.PageUtil;
import com.pramy.util.StringUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.pramy.model.MyFile;
import com.pramy.model.User;
import com.pramy.service.MyFileServiceImp;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Servlet implementation class FileServlet
 */
public class FileServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private ServletContext sc;
	private String savePath;
	private String temp;
	public void init(ServletConfig config)
	{
		savePath = config.getInitParameter("savePath");
		temp = config.getInitParameter("temp");//初始化上传文件路径
		sc = config.getServletContext();

	}
	private MyFileService ms;


	public void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String message=null;
		String mytype=null;
		MyFile myFile = new MyFile();
		myFile.setUserName(user.getUserName());
		String savePath =  this.savePath;
		String tmpPath =   sc.getRealPath("/")+this.temp;;
		File tmpFile = new File(tmpPath);
		if (!tmpFile.exists()) {
			tmpFile.mkdirs();
		}
		PrintWriter pr =response.getWriter();
		try {
			// 创建文件工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 100);
			factory.setRepository(tmpFile);

			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setProgressListener(new ProgressListener() {
				@Override
				public void update(long size, long finish, int remain) {
					System.out.println("文件大小：" + finish + ",已完完成：" + size);

				}
			});
			upload.setHeaderEncoding("utf-8");
			// 设置单个文件上传的大小
			upload.setFileSizeMax(1024 * 1024 * 10);
			// 设置总的上传大小
			upload.setSizeMax(1024 * 1024 * 50);

			if (!ServletFileUpload.isMultipartContent(request)) {
				return;
			}
			//获取参数
			List<FileItem> list = upload.parseRequest(request);
			for (FileItem fileItem : list) {
				if (fileItem.isFormField()) {
					String name = fileItem.getFieldName();
					String value = fileItem.getString("utf-8");
					System.out.println(name);
					System.out.println(value);
					request.setAttribute(name, value);
				} else {
					String fileName = fileItem.getName();
					System.out.println(fileName);
					if (fileName == null || "".equals(fileName.trim())) {
						continue;
					}
					System.out.println("fileNmae:"+fileName);
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					
					String fileExtName=null;
					if(fileName.lastIndexOf(".")!=-1){
						fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
					}else{
						fileExtName="other";
					}
					 if(JudgeUtil.isImage(fileExtName)){
						myFile.setFileType("image");
						savePath=savePath+"image"+"\\";
					}else{
						myFile.setFileType(fileExtName);						
					}
					InputStream in = fileItem.getInputStream();
					
					String saveFilename = makeFileName(fileName);
					String realSavePath = makePath(saveFilename, savePath);
					File path = new File(sc.getRealPath("/")+realSavePath);
					if(!path.exists()){
						path.mkdirs();
					}
					myFile.setCreatTime(new Date());
					myFile.setFileName(saveFilename);
					myFile.setFileUrl(realSavePath);
					
					FileOutputStream out = new FileOutputStream(this.sc.getRealPath("/")+realSavePath + "\\" + saveFilename);
					byte buffer[] = new byte[1024*10];
					int len = 0;
					while ((len = in.read(buffer)) > 0) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					fileItem.delete();
					ms.add(myFile);

				}
				message = "上传成功";
				request.setAttribute("message", message);
			}

		} catch (FileUploadBase.FileSizeLimitExceededException e) {
			e.printStackTrace();
			message ="失败，单个文件超出了最大值";
		} catch(FileUploadBase.SizeLimitExceededException e){
			e.printStackTrace();
			message ="失败，总文件超过了最大值";
		}catch (Exception e) {
			e.printStackTrace();
			message ="上传失败";
		} finally {
			mytype=(String)request.getAttribute("mytype");
			if(myFile==null){
				return;
			}			
			if(mytype.equals("chat")){
				OutputUtil.jsWarning(pr, message);
				return;
			}
			String servlet=null;
			if(mytype.equals("image")){
				servlet="ImageServlet?action=select";
			}else if(mytype.equals("file")) {
				servlet="FileServlet?action=select";
			}
			request.setAttribute("src/main/servlet",servlet );
			request.getRequestDispatcher("uploadResult.jsp").forward(request, response);
		}

	}
	
	public void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MyFile myFile = new MyFile();
		String pageSize=request.getParameter("pageSize");
		String pageNo = request.getParameter("pageNo");
		String select =  request.getParameter("select");
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
		request.setAttribute("totalPage",pageUtil.getTotalPage());

		List<MyFile> list = ms.selectList(myFile,pageUtil);

		//查询条件
		request.setAttribute("select", select);
		request.setAttribute("MyFileList", list);
		request.getRequestDispatcher("filedown.jsp").forward(request, response);
	}
	
	public void down (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       	String url = sc.getRealPath("/")+request.getParameter("url");  
	       	String fileName= request.getParameter("fileName");
	        File file = new File(url + fileName);
	        //如果文件不存在
	        OutputStream out = response.getOutputStream();
	        if(!file.exists()){
	        	String string="<script>alert('文件不存在');</script>";
	           out.write(string.getBytes());
	            return;
	        }
	        String realname = fileName.substring(fileName.indexOf("_")+1);
	        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
	        FileInputStream in = new FileInputStream(url + "\\" + fileName);
	        byte buffer[] = new byte[1024*10];
	        int len = 0;
	        while((len=in.read(buffer))>0){
	            out.write(buffer, 0, len);
	        }
	        in.close();
	        out.close();
	   
	}
	
	
	
	private String makeFileName(String filename) {
		return UUID.randomUUID().toString()+ "_" + filename;
	}

	public String makePath(String filename, String saveRootPath) {
		int hashcode = filename.hashCode();
		int dir1 = hashcode & 0xf; // 0--15
		int dir2 = (hashcode & 0xf0) >> 4; // 0-15
		String dir = saveRootPath + dir1 + "\\" + dir2+"\\";
		return dir;
	}
}
