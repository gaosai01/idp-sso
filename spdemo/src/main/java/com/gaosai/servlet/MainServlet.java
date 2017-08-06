package com.gaosai.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gaosai.common.Config;

public class MainServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String)session.getAttribute( Config.username );
		resp.addHeader( "Content-Type" , "text/html; charset=UTF-8");
		resp.getWriter().append( "用户名:" + username ).append("</br><a href=\""+Config.idpLogoutUrl+"\" >注销</a>").flush();;
	}

}
