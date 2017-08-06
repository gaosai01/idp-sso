package com.gaosai.idp.util;

import java.net.URLEncoder;
import java.util.Base64;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.gaosai.idp.entity.Sp;


public class AppUtil {
	
	public static String saltPwd( String pwd, String salt ){
		return new Md5Hash( pwd , salt ).toString();
	}
	
	/**
	 * 加密算法
	 * @param map
	 * @param sp
	 */
	public static void sign( TreeMap<String, String> map, Sp sp ){
		StringBuffer sb = new StringBuffer();
		boolean f = true;
		for( Entry<String, String> entry : map.entrySet() ){
			if( !f ){
				sb.append( "&" );
			}
			f = false;
			sb.append( entry.getKey() ).append( "=" ).append( entry.getValue() );
		}
		byte[] b = HMACSHA1.HmacSHA1Encrypt( sb.toString() , sp.getAppsecret() );
		if( b == null )return;
		String sign = Base64.getEncoder().encodeToString( b );
		sign = URLEncoder.encode( sign );
		map.put( "sign" , sign );
	}

}
