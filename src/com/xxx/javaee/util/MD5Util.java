/**
 * SecurityPass.java   
 * 
 * Date	2011-11-5  下午08:30:21
 * author 周忠友
 */
package com.xxx.javaee.util;

import java.security.MessageDigest;

/**
 * <i>PassSecurity</i>
 * 		对密码进行加密和验证的类  
 * @see
 * @author Zhou zhongyou
 * @since 1.0
 * @version 1.0 下午08:30:21
 */
public abstract class MD5Util {

    //十六进制下数字到字符的映射数组   
    private final static String[] hexDigits = {"a", "8", "c", "d", "0",
        "e", "f", "3", "z", "7","k","n","m","y","v","9"};
       

    /**  对字符串进行MD5加密     */  
    public final static String encode(String originString){
        if (originString != null){   
            try{
                //创建具有指定算法名称的信息摘要   
                MessageDigest md = MessageDigest.getInstance("MD5");   
                //使用指定的字节数组对摘要进行最后更新，然后完成摘要计算   
                byte[] results = md.digest(originString.getBytes());   
                //将得到的字节数组变成字符串返回   
                String resultString = byteArrayToHexString(results);   
                return resultString.toUpperCase();   
            } catch(Exception ex){
                ex.printStackTrace();
            }
        }   
        return null;   
    }   
       
    /**   
     * 转换字节数组为十六进制字符串
     * @param b 字节数组
     * @return    十六进制字符串
     */  
    private final static String byteArrayToHexString(byte[] b){   
        StringBuffer resultSb = new StringBuffer();   
        for (int i = 0; i < b.length; i++){   
            resultSb.append(byteToHexString(b[i]));   
        }   
        return resultSb.toString();   
    }   
       
    /** 将一个字节转化成十六进制形式的字符串     */  
    private final static String byteToHexString(byte b){   
        int n = b;   
        if (n < 0)   
            n = 256 + n;   
        int d1 = n / 16;   
        int d2 = n % 16;   
        return hexDigits[d1] + hexDigits[d2];   
    }   

}
