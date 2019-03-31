package com.xxx.javaee.servlet.ajax.teacher;

import com.xxx.javaee.bean.Teachar;
import com.xxx.javaee.bean.param.Pager;
import com.xxx.javaee.bean.param.TeaParam;
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

@WebServlet(name = "TeacherServlet",urlPatterns = "/teacher/query")
public class TeacherServlet extends HttpServlet {

    private TeacharService service = new TeacharServicelmpl();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TeaParam sp = ParamUtil.getParamBean(request,TeaParam.class);

        System.out.println(sp);


        Pager<Teachar> pager = service.query(sp,new Pager<Teachar>(sp.getCurPage(),sp.getPageSize()));


        // 把后端查询到的数据返回给前端
        Writer w = response.getWriter();
        w.write(JsonUtil.toJson(pager));
        w.flush();
        w.close();
    }
}
