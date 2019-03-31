package com.xxx.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @WebServlet()——注解类
 * 其对应Web.xml 对于Servlet的配置
 * 在这里我们只需要urlPatterns 属性
 * 此注解简化了Web.xml 的配置
 */
@WebServlet(urlPatterns = "/test1")
public class Test1Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
