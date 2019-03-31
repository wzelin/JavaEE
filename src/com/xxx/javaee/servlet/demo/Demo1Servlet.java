package com.xxx.javaee.servlet.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/demo1")
public class Demo1Servlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("首次运行时执行初始化方法");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入doPost方法");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入doGet方法");
    }
    @Override
    public void destroy() {
        System.out.println("结束时执行销毁方法");
    }
}
