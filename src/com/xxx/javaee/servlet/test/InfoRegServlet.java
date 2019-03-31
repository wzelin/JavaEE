package com.xxx.javaee.servlet.test;

import com.xxx.javaee.bean.Info;
import com.xxx.javaee.bean.param.InfoRegParam;
import com.xxx.javaee.service.InfoService;
import com.xxx.javaee.service.InfoServiceImpl;
import com.xxx.javaee.servlet.test.MyResponse;
import com.xxx.javaee.util.CheckCode;
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

@WebServlet(name = "InfoRegServlet",urlPatterns = "/info/reg")
public class InfoRegServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        InfoService service = new InfoServiceImpl();
        MyResponse myResponse = null;
        List<Info> list = service.quemy();
        InfoRegParam info = ParamUtil.getParamBean(request,InfoRegParam.class);
        if(!info.getCheckCode().equals(CheckCode.getChecCode(request))){
            myResponse = new MyResponse(false,"验证码输入错误！");
        } else{
            int a = 0;
            for (Info i : list) {
                if (i.getName().equals(info.getName())) {
                    a++;
                    if(i.getPassword().equals(info.getPass())) {
                     myResponse =  new MyResponse(true, list, "用户登录成功");
                    } else {
                        myResponse = new MyResponse(false, "密码输入错误");
                    }
                }
            }
            if (a == 0) {
                myResponse = new MyResponse(false, "用户名输入错误");

            }
        }
        Writer w = response.getWriter();
        w.write(JsonUtil.toJson(myResponse));
        w.flush();
        w.close();
    }
}
