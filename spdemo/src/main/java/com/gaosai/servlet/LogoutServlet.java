package com.gaosai.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gaosai.common.Config;

public class LogoutServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doGet(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().invalidate();
		resp.getWriter().append( "callback("+ "{\"code\":\"success\",\"msg\":\"demo程序推出注销成功\"}" + ")" );
		resp.addHeader( "Content-Type" , "application/jsonp; charset=UTF-8");
	}
	
}
