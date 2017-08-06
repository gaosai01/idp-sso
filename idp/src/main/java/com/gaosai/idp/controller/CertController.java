package com.gaosai.idp.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.print.DocFlavor.STRING;
import javax.servlet.http.HttpSession;

import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gaosai.idp.common.Config;
import com.gaosai.idp.entity.Sp;
import com.gaosai.idp.mapping.ISpMapping;
import com.gaosai.idp.util.AppUtil;
import com.gaosai.idp.util.DateUtil;

@Controller
public class CertController extends BaseController{
	
	@Autowired
	private ISpMapping spDao;
	
	@RequestMapping( "tocert" )
	public Object tocert( String appkey, RedirectAttributes attr, HttpSession session ){
		String username = username();
		if( StringUtils.isEmpty( appkey ) ){
			appkey = Config.def_appkey;
		}
		if( username == null ){
			attr.addAttribute( "appkey" , appkey );
			return "redirect:/login";
		}
		Sp sp = spDao.getSp( appkey );
		if( sp == null ){
			attr.addAttribute( "appkey" , Config.def_appkey );
			return "redirect:/tocert";
		}
		TreeMap<String, String> map = new TreeMap<String, String>();
		map.put( "uid" , username );
		map.put( "ttl" , DateUtil.getCurTimeStamp() + 300 + "" );
		AppUtil.sign( map , sp );
		attr.addAllAttributes( map );
		List<String> ls = (List<String>)session.getAttribute( Config.SESSION.splist );
		if( ls == null ){
			ls = new LinkedList<String>();
			session.setAttribute( Config.SESSION.splist , ls );
		}
		ls.add( sp.getLogout() );
		return "redirect:" + sp.getRedirect();
	}
	
	

}
