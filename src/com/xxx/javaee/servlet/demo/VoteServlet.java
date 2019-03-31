package com.xxx.javaee.servlet.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "VoteServlet",urlPatterns = "/vote")
public class VoteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String ip = request.getRemoteAddr();
        ServletContext context = request.getServletContext();
        Map<String,String> map =
                (Map<String,String>)context.getAttribute("vote");
        System.out.println(map);
        if(map == null){
            map = new HashMap<>();
        }
            map.put(ip, name);
            context.setAttribute("vote", map);
            //请求转发
            RequestDispatcher rd =
                    request.getRequestDispatcher("/test/vote.jsp");
            rd.forward(request, response);

    }
}
