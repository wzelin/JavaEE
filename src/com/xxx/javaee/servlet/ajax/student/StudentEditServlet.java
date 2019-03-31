package com.xxx.javaee.servlet.ajax.student;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.bean.param.StuDeleteParam;
import com.xxx.javaee.service.StudentService;
import com.xxx.javaee.service.StudentServiceImpl;
import com.xxx.javaee.util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/student/edit")
public class StudentEditServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentService stuser = new StudentServiceImpl();
        Student stu = new Student();
        stu = ParamUtil.getParamBean(request,Student.class);
        System.out.println(stu);
        stuser.edit(stu);
    }
}
