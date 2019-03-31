package com.xxx.javaee.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebFilter(filterName = "EncodingConvertorFilter",
urlPatterns = "/*")
// /* 表示所有的请求都经过该过滤器
public class EncodingConvertorFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (request.getQueryString() != null) {
            final Map<String, String[]> parameterMap = new HashMap<String, String[]>();
            Enumeration<?> names = request.getParameterNames();
            while (names.hasMoreElements()) {
                String paramName = (String) names.nextElement();
                String[] paramValue = request.getParameterValues(paramName);
                for (int i = 0; i < paramValue.length; i++) {
                    if (paramValue[i] != null && paramValue[i].length() != 0) {
                        paramValue[i] = new String(paramValue[i].getBytes("ISO-8859-1"), "UTF-8");
                    }
                }
                parameterMap.put(paramName, paramValue);
            }
            chain.doFilter(new HttpServletRequestWrapper(request) {

                public String getParameter(String name) {
                    if (parameterMap.get(name) == null)
                        return null;
                    return (String) parameterMap.get(name)[0];
                }

                public Map<String, String[]> getParameterMap() {
                    return parameterMap;
                }

                public Enumeration<String> getParameterNames() {
                    return Collections.enumeration(parameterMap.keySet());
                }

                public String[] getParameterValues(String name) {
                    return parameterMap.get(name);
                }

            }, response);
        } else {
            chain.doFilter(request, response);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
