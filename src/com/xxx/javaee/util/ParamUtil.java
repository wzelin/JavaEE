package com.xxx.javaee.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class ParamUtil {

    public static final <T> T getParamBean(HttpServletRequest request, Class<T> clazz) throws IOException {
        // 读取请求内容
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));
        String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return JsonUtil.toBean(sb.toString(),clazz);
    }

}
