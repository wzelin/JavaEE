package com.xxx.javaee.servlet.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/demo2")
public class Demo2Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //-- 获取相关路径的方法
        System.out.println("getPathInfo："+request.getPathInfo());
        System.out.println("getPathTranslated:"+request.getPathTranslated());
        System.out.println("getContextPath:"+request.getContextPath());
        System.out.println("getRealPath:"+request.getRealPath("/"));
        System.out.println("getServletPath:"+request.getServletPath());

//-- 获取相关ip或端口的方法
        System.out.println("getLocalAddr："+request.getLocalAddr());
        System.out.println("getServerName："+request.getServerName());//--获取本地域名
        System.out.println("getLocalPort："+request.getLocalPort());
        System.out.println("getLocalName："+request.getLocalName());
        System.out.println("getRemoteAddr："+request.getRemoteAddr());
        System.out.println("getRemoteHost："+request.getRemoteHost());
//--获取的是host文件中从前往后第一个与本机IP一致的域名，效率较getRemoteAddr低
        System.out.println("getRemotePort："+request.getRemotePort());
        System.out.println("getRemoteUser："+request.getRemoteUser());


//-- 获取http头信息
        System.out.println("=======================================");
        System.out.println("getHeader(\"Host\")："+request.getHeader("Host"));
        Enumeration<String> enums = request.getHeaderNames();
        while(enums.hasMoreElements()){
            String headerName = enums.nextElement();
            System.out.println(headerName+"："+request.getHeader(headerName));
        }
        System.out.println("getContentLength:"+request.getContentLength());
        System.out.println("getContentType:"+request.getContentType());
        System.out.println("getMethod:"+request.getMethod());
        System.out.println("getProtocol:"+request.getProtocol());


//--获取或设置request级别的属性值
        System.out.println("=======================================");
        System.out.println(request.getAttribute("name"));//-- 还没有设置该属性的值，返回null
        request.setAttribute("name", "对象");//-- 设置属性名称为name的属性值为"对象"字符串
        System.out.println(request.getAttribute("name"));//-- 已经设置了属性的值，返回"对象"

        request.setAttribute("name", "对象2");//-- 再次设置属性name的值为"对象2"，该值会覆盖之前的设置

        Enumeration<String> enum2 = request.getAttributeNames();
//-- 目前该request对象只设置了一个name属性，所以枚举类中只有一个值
        while(enum2.hasMoreElements()){
            String str = enum2.nextElement();
            System.out.println(str+"："+request.getAttribute(str));
        }



//---获取或设置request请求编码格式
        System.out.println("getCharacterEncoding:"+request.getCharacterEncoding());
        request.setCharacterEncoding("UTF-8");
        System.out.println("getCharacterEncoding:"+request.getCharacterEncoding());

    }
}
