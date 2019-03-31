package com.xxx.javaee.service;

import com.xxx.javaee.bean.Info;
import com.xxx.javaee.bean.param.InfoQueryParam;
import com.xxx.javaee.dao.InfoDAO;
import com.xxx.javaee.dao.InfoDAOImpl;

import java.util.List;

public class InfoServiceImpl implements InfoService {
    InfoDAO dao = new InfoDAOImpl();
    @Override
    public List<Info> quemy() {
        return dao.quemy();
    }

    @Override
    public List<Info> quemy(InfoQueryParam info) {
        return dao.quemy(info);
    }
}
