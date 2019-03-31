package com.xxx.javaee.dao;


import com.xxx.javaee.bean.Info;
import com.xxx.javaee.bean.param.InfoQueryParam;

import java.util.List;

public interface InfoDAO {

    List<Info> quemy();
    List<Info> quemy(InfoQueryParam info);

}
