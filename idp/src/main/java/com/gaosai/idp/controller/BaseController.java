package com.gaosai.idp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.gaosai.idp.common.Config;

public class BaseController {
	
	public String username(){
		HttpSession session = getSession();
		if( session == null ){
			return null;
		}
		return (String)session.getAttribute( Config.SESSION.username );
	}
	
	
	public HttpSession getSession() { 
	    HttpSession session = null; 
	    try { 
	        session = getRequest().getSession(); 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    } 
	    return session; 
	} 
	    
	public HttpServletRequest getRequest() { 
	    ServletRequestAttributes attrs =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
	    return attrs.getRequest(); 
	}

}
