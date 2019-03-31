package com.xxx.javaee.servlet.test;

import com.xxx.javaee.bean.Info;
import com.xxx.javaee.bean.param.InfoQueryParam;
import com.xxx.javaee.bean.param.InfoRegParam;
import com.xxx.javaee.service.InfoService;
import com.xxx.javaee.service.InfoServiceImpl;
import com.xxx.javaee.util.JsonUtil;
import com.xxx.javaee.util.ParamUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

@WebServlet(name = "InfoQueryServlet",urlPatterns = "/info/query")
public class InfoQueryServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        InfoService service = new InfoServiceImpl();
        InfoQueryParam info = ParamUtil.getParamBean(request,InfoQueryParam.class);

        List<Info> list = service.quemy(info);

        Writer w = response.getWriter();
        w.write(JsonUtil.toJson(list));
        w.flush();
        w.close();
    }
}
