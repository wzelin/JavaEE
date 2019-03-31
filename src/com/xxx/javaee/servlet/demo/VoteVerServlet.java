package com.xxx.javaee.servlet.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "VoteVerServlet", urlPatterns = "/vote/verify")
public class VoteVerServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ip = request.getRemoteAddr();
        ServletContext context = request.getServletContext();
        Map<String,String> map =
                (Map<String,String>)context.getAttribute("vote");
        System.out.println(map);
        if(map != null){
            Set<String> set = map.keySet();
            for (String id: set){
                if(ip.equals(id)){
                    Writer w = response.getWriter();
                    w.write("true");
                    w.flush();
                    w.close();
                }
            }
        }

    }

}
