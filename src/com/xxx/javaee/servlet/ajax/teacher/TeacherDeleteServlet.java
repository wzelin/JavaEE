package com.xxx.javaee.servlet.ajax.teacher;

import com.xxx.javaee.bean.Teachar;
import com.xxx.javaee.bean.param.TeaDelParam;
import com.xxx.javaee.service.StudentService;
import com.xxx.javaee.service.StudentServiceImpl;
import com.xxx.javaee.service.TeacharService;
import com.xxx.javaee.service.TeacharServicelmpl;
import com.xxx.javaee.util.JsonUtil;
import com.xxx.javaee.util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name = "TeacherDeleteServlet",urlPatterns = "/teacher/delete")
    public class TeacherDeleteServlet extends HttpServlet {

    private TeacharService service = new TeacharServicelmpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TeaDelParam s = ParamUtil.getParamBean(request,TeaDelParam.class);

        System.out.println(s);

        service.delete(s.getId());

        // 把后端查询到的数据返回给前端
        Writer w = response.getWriter();
        w.write(JsonUtil.toJson(new MyResponse(true,"删除学生信息成功")));
        w.flush();
        w.close();
    }
}
