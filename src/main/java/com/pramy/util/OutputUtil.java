package com.pramy.util;

import java.io.PrintWriter;

public class OutputUtil {

	public static void jsWarning(PrintWriter out,String title){
		out.println("<script>");
		out.print("alert('");
		out.print(title);
		out.println("');history.back(-1);");
		out.println("</script>");
		out.flush();
		out.close();
	}
}
