package com.xxx.javaee.jdbc;

import java.io.UnsupportedEncodingException;

/**
 * @author :周忠友
 * @date   ：2017年8月21日 下午3:45:04
 * @since  : 1.0
 * 
 */
public class StrUtil {
	
	/***
	 * 获取包含中文的字符串的长度
	 * 
	 * @param str 要计算长度的字符串
	 * @param encoding 当前字符的编码格式
	 * @return 字符串的长度 
	 * 如果字符编码错误返回-1
	 */
	public static final long getStrLen(String str,String encoding){
		try {
			String realStr = new String(str.getBytes(encoding), "ISO8859_1");
			return realStr.length();
		} catch (UnsupportedEncodingException e) {
			return -1;
		}		 
	}
	
	/***
	 * 获取包含中文的字符串的长度
	 * 默认按照UTF-8编码计算
	 * 
	 * @param str 要计算长度的字符串
	 * @return 字符串的长度 
	 * 如果字符编码错误返回-1
	 */
	public static final long getStrLen(String str){
		return getStrLen(str, "UTF-8");	 
	}
	
	/***
	 * 将形如fieldNamed的java字段名称fieldName变成形如field_name的SQL字段定义形式
	 * 
	 * @param fieldName
	 * @return
	 */
	public final static String toSqlField(String fieldName){
		String lastStr = fieldName.substring(fieldName.length()-1);
		if(lastStr.matches("[A-Z]"))
			fieldName = fieldName.substring(0, fieldName.length() - 1)+"_"+lastStr.toLowerCase();
		
		String k[] = fieldName.split("[A-Z]");
		int len = k.length;
		int strLen = k[0].length();
		StringBuffer result = new StringBuffer();
		if(len > 1)
			result.append(k[0]+"_"+Character.toLowerCase(fieldName.charAt(strLen)));
		for(int i = 1; i < len - 1; i++){
			strLen = k[i].length() + strLen + 1;
			char s = Character.toLowerCase(fieldName.charAt(strLen));
			result.append(k[i]+"_"+s);
		}
		result.append(k[len - 1]);
		
		return result.toString();
	}
	
	
	/**
	 * 将field_name转换成fieldName的形式
	 * @param sqlName
	 * @return
	 */
	public final static String toObjField(String sqlName){
		String[] names = sqlName.toLowerCase().split("_");
		StringBuffer sf = new StringBuffer();
		int len = names.length;
		for(int i = 0 ; i < len ;i++){
			
			if(i == 0){
				sf.append(names[i]);
			}else{
				sf.append(firstUpper(names[i]));
			}
		}
		return sf.toString();
	}
	
	/**
	 * 将字符的首字大写
	 * @param name
	 * @return
	 */
	public final static String firstUpper(String name){		
		String first = name.substring(0, 1).toUpperCase();
		String rest = name.substring(1, name.length());
		return new StringBuffer(first).append(rest).toString(); 
	}
	
	/**
	 * 将字符的首字小写
	 * @param name
	 * @return
	 */
	public final static String firstLower(String name){
		String first = name.substring(0, 1).toLowerCase();
		String rest = name.substring(1, name.length());
		return new StringBuffer(first).append(rest).toString(); 
	}

}
