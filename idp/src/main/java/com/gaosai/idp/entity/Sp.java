package com.gaosai.idp.entity;

public class Sp {
	
	private Integer id;
	private String appkey;
	private String appsecret;
	private String time;
	private String redirect;
	private String logout;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getAppsecret() {
		return appsecret;
	}
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRedirect() {
		return redirect;
	}
	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	public String getLogout() {
		return logout;
	}
	public void setLogout(String logout) {
		this.logout = logout;
	}

}
