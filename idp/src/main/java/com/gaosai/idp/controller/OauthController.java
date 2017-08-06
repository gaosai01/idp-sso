package com.gaosai.idp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaosai.idp.common.Config;
import com.gaosai.idp.common.Result;
import com.gaosai.idp.entity.Sp;
import com.gaosai.idp.entity.User;
import com.gaosai.idp.mapping.ISpMapping;
import com.gaosai.idp.mapping.IUserMapping;
import com.gaosai.idp.util.AppUtil;

@Controller
public class OauthController extends BaseController{
	
	private static final Logger log = Logger.getLogger( OauthController.class );
	
	@Autowired
	private IUserMapping userDao;
	@Autowired
	private ISpMapping spDao;
	
	@RequestMapping( "/doLogin" )
	@ResponseBody
	public Object login( String appkey, String username, String pwd, HttpSession session ){
		if( username() != null ){
			return Result.error( "用户已登陆" );
		}
		if( StringUtils.isEmpty( appkey ) ){
			appkey = Config.def_appkey;
		}
		if( StringUtils.isEmpty( username ) ){
			return Result.error( "用户名不能为空" );
		}
		if( StringUtils.isEmpty( pwd ) ){
			return Result.error( "密码不能为空" );
		}
		Sp sp = spDao.getSp( appkey );
		if( sp == null ){
			return Result.error( "sp的appkey不正确" );
		}
		User user = userDao.getUser( username );
		if( user == null ){
			return Result.error( "用户不存在" );
		}
		pwd = AppUtil.saltPwd( pwd , user.getTime() );
		if( !pwd.equals( user.getPassword() ) ){
			return Result.error( "密码不正确" );
		}
		session.setAttribute( Config.SESSION.username , user.getUsername() );
		Map<String, String> map = new HashMap<String, String>();
		map.put( "redirect" , Config.certRedirectUrl + "?appkey=" + appkey );
		return Result.success( map , "登录成功"  );
	}
	
	@RequestMapping( "login" )
	public Object login( String appkey, RedirectAttributes attr ){
		if( username() != null ){
			attr.addAttribute( "appkey" , appkey );
			return "redirect:/tocert";
		}
		return new ModelAndView( "/WEB-INF/login.jsp", "appkey", appkey );
	}
	
	@RequestMapping( "logout" )
	public Object logout( HttpSession session ){
		if( username() == null ){
			return "redirect:/login";
		}
		List<String> ls = (List<String>)session.getAttribute( Config.SESSION.splist );
		ModelAndView mv = new ModelAndView( "/WEB-INF/logout.jsp" );
		mv.addObject( "loginUrls" , ls );
		session.invalidate();
		return mv;
	}
	
}
