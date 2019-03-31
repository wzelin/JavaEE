package com.xxx.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * 自定义的Servlet必须继承
 * javax.servlet.http.HttpServlet
 * 并重写doGet,doPost或doService方法中的至少一个
 */
public class HelloServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
       //request——请求对象
        //response——响应对象
        response.setCharacterEncoding("UTF-8");//用"UTF-8"输出到Writer
        response.setContentType("text/html;charset = UTF-8");//输出的html文件用UTF-8格式

        Writer writer = response.getWriter();
        writer.write("Hello Servlet 嗨!");
        writer.flush();
        writer.close();
    }
}
