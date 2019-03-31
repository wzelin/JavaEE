package com.xxx.javaee.servlet;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.service.StudentService;
import com.xxx.javaee.service.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns ="/test3")
public class Test3Servlet extends HttpServlet {

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String name = request.getParameter("name");
      String age = request.getParameter("age");
      String sex = request.getParameter("sex");
      String[] favs = request.getParameterValues("fav");
      String edu = request.getParameter("edu");
      String desc = request.getParameter("desc");
      System.out.println(name);
      System.out.println(age);
      System.out.println(sex);
      System.out.println(Arrays.asList(favs));
      System.out.println(edu);
      System.out.println(desc);

      Student student = new Student();
      student.setName(name);
      student.setAge(Integer.valueOf(age));
      student.setSex(Integer.valueOf(sex));
      //使字符数组变成有分割符的字符串
      StringBuilder sb = new StringBuilder();
      for (String srt: favs) {
          sb.append(srt).append(",");
      }
      student.setFavs(sb.toString().substring(0,sb.length()-1));
      student.setEdu(Integer.parseInt(edu));
      student.setDesc(desc);
      System.out.println(student);
      StudentService studentService = new StudentServiceImpl();
      studentService.insert(student);
    }
}
