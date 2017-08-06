package com.gaosai.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gaosai.common.Config;

public class CheckUser implements Filter{

	public void destroy() {
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)arg0;
		HttpSession session = req.getSession();
		arg0.setCharacterEncoding( "UTF-8" );
		arg1.setCharacterEncoding( "UTF-8" );
		if( "/doLogin".equals( req.getServletPath() ) || "/doLogout".equals( req.getServletPath() ) ){
			arg2.doFilter(arg0, arg1);
			return;
		}
		Object obj = session.getAttribute( Config.username );
		if( obj == null ){
			HttpServletResponse res = (HttpServletResponse)arg1;
			res.sendRedirect( Config.idpLoginUrl );
		}else{
			arg2.doFilter(arg0, arg1);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}
	

}
