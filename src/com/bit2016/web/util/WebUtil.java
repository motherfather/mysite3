package com.bit2016.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil {
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws IOException, ServletException {
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url)  throws IOException, ServletException {
		response.sendRedirect(url);
	}
<<<<<<< HEAD
	
	public static int checkIntParam(String s, int value) {
		return (s != null && s.matches("\\d*\\.?\\d+")) ? Integer.parseInt(s) : value;
	}
=======
>>>>>>> branch 'master' of https://github.com/motherfather/mysite3.git
}
