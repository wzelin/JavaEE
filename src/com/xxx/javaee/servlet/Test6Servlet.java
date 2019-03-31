package com.xxx.javaee.servlet;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.service.StudentService;
import com.xxx.javaee.service.StudentServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/test6")
public class Test6Servlet extends HttpServlet {
    StudentService service = new StudentServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String Id = request.getParameter("id");
       int id = Integer.parseInt(Id);
       service.delete(id);

        Map<String,Object> whereMap = new HashMap<>();
        List<Student> list = service.quemy(whereMap);
        System.out.println(list);
        //设置request属性值
        request.setAttribute("list",list);

        //请求转发
        RequestDispatcher rd =
                request.getRequestDispatcher("/test/student_list.jsp");
        rd.forward(request,response);
    }
}
