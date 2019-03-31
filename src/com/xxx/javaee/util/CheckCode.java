/**
 * CheckCode.java
  *  
 * Date	2010-8-18  下午02:17:17
 * author Zhou Zhongyou
 */
package com.xxx.javaee.util;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * A <i>CheckCode</i>
 * <p>
 * Specification:
 * 		生成验证码
 * </p>
 * 
 * @see
 * @author 周忠友
 * @since 1.0
 * @version 1.0 2010-8-18
 */
public abstract class CheckCode {

	public static final String CHECKCODE_NAME = "check_code";
	
	public static final String[] CODE_ARR = {"0","1","2","3","4","5","6","7","8","9","a",
		"b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	
	/**
	 * 图片宽度
	 */
	public static final int IMG_WIDTH = 100;
	/**
	 * 图片高度
	 */
	public static final int IMG_HEIGHT = 32;
	
	public static final int FONT_SIZE = 35;
	
	
	public final static String getChecCode(HttpServletRequest request){
		HttpSession session = request.getSession();
		return session.getAttribute(CHECKCODE_NAME).toString();
	}
	  
	 public final static void createCheckCode(HttpServletRequest request,   
	   HttpServletResponse response)throws IOException{   
		 response.reset();
		  response.setContentType("image/jpeg");   
		// 禁止图像缓存。
		  response.setHeader("Pragma", "No-cache");   
		  response.setHeader("Cache-Control", "no-cache");   
		  response.setDateHeader("Expires", 0);   
		  HttpSession session = request.getSession();   
		 		 
		  BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,   
		    BufferedImage.TYPE_INT_RGB);   
		  
		  // 获取图形上下文   
		  Graphics g = image.getGraphics();   
		  
		  // 生成随机类   
		  Random random = new Random();   
		  
		  // 设定背景色   
		  g.setColor(getRandColor(200, 250));   
		  g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);   
		  
		  // 设定字体   
		  g.setFont(new Font("Times New Roman", Font.ITALIC, FONT_SIZE));   
		  
		  // 随机产生120条干扰线，使图象中的认证码不易被其它程序探测到   
		  g.setColor(getRandColor(100, 200));   
		  for (int i = 0; i < 120; i++) {   
		   int x = random.nextInt(IMG_WIDTH);   
		   int y = random.nextInt(IMG_HEIGHT);   
		   int xl = random.nextInt(10);   
		   int yl = random.nextInt(10);   
		   g.drawLine(x, y, x + xl, y + yl);   
		  }   
		  
		  // 取随机产生的认证码(4位数字)   
		  String sRand = "";   
		  for (int i = 0; i < 4; i++) {   
		   String rand = String.valueOf(CODE_ARR[random.nextInt(35)]);   
		  sRand += rand;   
		   // 将认证码显示到图象中   
		   g.setColor(new Color(20 + random.nextInt(110), 20 + random   
		     .nextInt(110), 20 + random.nextInt(110)));// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成   
		   g.drawString(rand, 20 * i+5, 26);   
		  }   
		  // 将认证码存入SESSION   
		  session.setAttribute(CHECKCODE_NAME, sRand);
		  session.setMaxInactiveInterval(1000*60*10);// 10分钟内有效
		  // 图象生效   
		  g.dispose();   
		  OutputStream responseOutputStream = null;
			responseOutputStream = response.getOutputStream();
			// 输出图象到页面   
			ImageIO.write(image, "JPEG", responseOutputStream);
		 // 以下关闭输入流！
		 responseOutputStream.flush();
		 responseOutputStream.close();
	    
	 }   
	  
	private final static Color getRandColor(int fc, int bc) {   
	  // 给定范围获得随机颜色   
	  Random random = new Random();   
	  if (fc > 255)   
	   fc = 255;   
	  if (bc > 255)   
	   bc = 255;   
	  int r = fc + random.nextInt(bc - fc);   
	  int g = fc + random.nextInt(bc - fc);   
	  int b = fc + random.nextInt(bc - fc);   
	  return new Color(r, g, b);   
	 }    	
}   