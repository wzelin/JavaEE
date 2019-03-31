package com.xxx.javaee.servlet.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/Demo3")
public class Demo3Servlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        ServletContext context = request.getServletContext();
        HttpSession session = request.getSession();

        context.setAttribute("context",1);
        session.setAttribute("context",1);
        request.setAttribute("context",1);


    }
}
