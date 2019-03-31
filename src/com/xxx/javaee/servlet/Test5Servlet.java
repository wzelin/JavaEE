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

@WebServlet(urlPatterns = "/test5")
public class Test5Servlet extends HttpServlet {
private StudentService service = new StudentServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.用getParameter()获取参数（后端getParameter()方法返回的都是String类型）
        String name = request.getParameter("name");
        String ageS = request.getParameter("age_s");
        String ageE = request.getParameter("age_e");

        //2.做类型转换
        //3.后端验证（此处省略）
        //4.处理参数
        Map<String,Object> whereMap = new HashMap<>();
        if(name != null || "".equals(name)){
            whereMap.put("name",name);
        }
        if(ageS != null || "".equals(ageS)){
            whereMap.put("ageS",ageS);
        }
        if(ageE != null || "".equals(ageE)){
            whereMap.put("ageE",ageE);
        }
        System.out.println(whereMap);
        //5.调用service
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
