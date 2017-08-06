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

public class LoginServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		doGet(arg0, arg1);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> map = req.getParameterMap();
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		for( Entry<String, String[]> entry : map.entrySet() ){
			if( entry.getValue().length > 0 ){
				treeMap.put( entry.getKey() , entry.getValue()[0] );
			}
		}
		StringBuffer sb = new StringBuffer();
		boolean first = true;
		for( Entry<String, String> entry : treeMap.entrySet() ){
			if( "sign".equals( entry.getKey() ) ){
				continue;
			}
			if( !first ){
				sb.append( "&" );
			}
			first = false;
			sb.append( entry.getKey() ).append( "=" ).append( entry.getValue() );
		}
		
		byte[] b = HmacSHA1Encrypt( sb.toString() , Config.appsecret );
		if( b == null ){
			resp.getWriter().append( "不正确" ).flush();
			return;
		}
		String sign = Base64.getEncoder().encodeToString( b );
		sign = URLEncoder.encode( sign );
		if( sign != null ){
			if( sign.equals( treeMap.get( "sign" ) ) ){
				req.getSession().setAttribute( Config.username , treeMap.get( "uid" ));
				resp.sendRedirect( req.getServletContext().getContextPath() );
				return;
			}
		}
		resp.getWriter().append( "不正确" ).flush();
	}
	
	
	private static final String MAC_NAME = "HmacSHA1";
	private static final String ENCODING = "UTF-8";

	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * 
	 * @param encryptText
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] HmacSHA1Encrypt(String encryptText, String encryptKey) {
		try {
			byte[] data = encryptKey.getBytes(ENCODING);
			// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
			SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
			// 生成一个指定 Mac 算法 的 Mac 对象
			Mac mac = Mac.getInstance(MAC_NAME);
			// 用给定密钥初始化 Mac 对象
			mac.init(secretKey);
			byte[] text = encryptText.getBytes(ENCODING);
			// 完成 Mac 操作
			return mac.doFinal(text);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
