package com.xxx.javaee.service;

import com.xxx.javaee.bean.Info;
import com.xxx.javaee.bean.param.InfoQueryParam;

import java.util.List;

public interface InfoService {
    List<Info> quemy();
    List<Info> quemy(InfoQueryParam info);
}
