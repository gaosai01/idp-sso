package com.gaosai.idp.common;

public class Config {
	
	public static final String def_appkey = "b15e76a41f50e";
	public static final String domain = "http://localhost:8080/idp/";
	public static final String certRedirectUrl = domain + "tocert";
	
	public static final class SESSION{
		public static final String username = "idp_username";
		public static final String splist = "idp_splist";
	}
	
	/**
	 * 此处需要定义一些状态码
	 */
	public static final class CODE{
		public static final int NO_LOGIN = 1;
		public static final int OTHER_LOGIN = 2;
		public static final int SUCCESS = 3;
		public static final int ERROR = 4;
		public static final int SIGN_ERROR = 5;
	}
}
