package com.xxx.javaee.servlet.ajax.student;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.bean.param.StuParam;
import com.xxx.javaee.service.StudentService;
import com.xxx.javaee.service.StudentServiceImpl;
import com.xxx.javaee.util.JsonUtil;
import com.xxx.javaee.util.ParamUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = "/student/query")
public class StudentQueryServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentService stuser = new StudentServiceImpl();
        StuParam sp = new StuParam();
           sp = ParamUtil.getParamBean(request,StuParam.class);
        System.out.println(sp);
        List<Student> list = stuser.quemy(sp);
        System.out.println(list);
        //返回JSON对象到前端
        if(list == null){
            list = new ArrayList<>();
        }

        Writer w = response.getWriter();
        w.write(JsonUtil.toJson(list));
        w.flush();
        w.close();


    }
}
