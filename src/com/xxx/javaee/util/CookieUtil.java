/**
 * CookieUtil.java
 * 
 * Date 2011-11-17 下午03:00:30
 * author 周忠友
 */
package com.xxx.javaee.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/**
 * A <i>CookieUtil</i>
 * <p>
 * Specification:
 * 	cokie存取器
 * </p>
 * 
 * @see
 * @author Zhou Zhongyou
 * @since 1.0
 * @version 1.0 2011-11-17
 */
public abstract class CookieUtil{

	private static String ENCODING = "UTF-8";

	public static final String getEncoding() {
		return ENCODING;
	}

	public static final void setEncoding(String encoding) {
		CookieUtil.ENCODING = encoding;
	}

	/***
	 * 批量设置cookie
	 * 
	 * @param m 要设置的cookie的map键子对
	 * @param maxAge  cookie的存在时间 以秒为单位 
	 * @param response 响应
	 */
	public final static void addCokies(Map<String,String> m,int maxAge,String domain,HttpServletResponse response){
		Iterator<?> iterator = m.keySet().iterator();
		while(iterator.hasNext()){
			String id = iterator.next().toString();
			addCookie(id,m.get(id),maxAge,domain,response);
		}
	}
	
	/***
	 * 添加cookie
	 * 
	 * @param id  cookie id
	 * @param val cookie value
	 * @param maxAge cookie的存在时间  以秒为单位 
	 * @param response 响应
	 */
	public final static void addCookie(String id,String val,int maxAge,String domain,HttpServletResponse response){
		try{
			Cookie tgc = new Cookie(id, URLEncoder.encode(val, ENCODING));
			tgc.setDomain(domain);
			tgc.setPath("/");
			tgc.setMaxAge(maxAge);
			tgc.setHttpOnly(true);//-- 禁止js操作该cookie
			response.addCookie(tgc);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/***
	 * 获取已储存的cookie
	 * 
	 * @param request 请求
	 * @return 所有cookie的map对象
	 */
	public final static Map<String,String> getCookies(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
        if (request == null)
            return map;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();                
                try {
					cookieValue = URLDecoder.decode(cookieValue, ENCODING);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
                
                map.put(cookieName, cookieValue);
            }
        }
        
        return map;

    }
	
	/**
	 * 获取name对应的cookie的值
	 * @param name cookie名称
	 * @param request 请求对象
	 * @return 对应cookie的值
	 */
	public final static String getCookie(String name,HttpServletRequest request){
		
		Cookie[] cookies = request.getCookies();
        if (cookies != null && name != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();                
                try {
					cookieValue = URLDecoder.decode(cookieValue, ENCODING);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
                
                if(name.equals(cookieName)){
                	return cookieValue;
                }
            }
        }
        
        return null;
	}
	
	/***
	 * 清空指定的cookie
	 * 
	 * @param request 请求对象
	 * @param cookieNames cookie的名称数组
	 */
	public final static void clearCookie(String[] cookieNames,HttpServletRequest request){
		int len = cookieNames.length;
		Cookie[] cookies = request.getCookies();
		int clen = cookies.length;
		for(int i = 0;i < clen; i++){
			for(int j = 0;j < len; j ++){
				if(cookies[i].getName().equals(cookieNames[j])){
					cookies[i].setMaxAge(-1);
				}				
			}
		}
	}
	/***
	 * 清空指定的cookie
	 *
	 * @param request 请求对象
	 * @param cookieName cookie名称
	 */
	public final static void clearCookie(String cookieName,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		int clen = cookies.length;
		for(int i = 0;i < clen; i++){
			if(cookies[i].getName().equals(cookieName)){
				cookies[i].setMaxAge(-1);
			}
		}
	}
}
