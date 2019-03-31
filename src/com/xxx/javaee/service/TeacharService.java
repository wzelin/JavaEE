package com.xxx.javaee.service;


import com.xxx.javaee.bean.Teachar;
import com.xxx.javaee.bean.param.Pager;
import com.xxx.javaee.bean.param.TeaParam;

public interface TeacharService {

    void insert(Teachar teachar);

    Pager<Teachar> query(TeaParam param, Pager<Teachar> pager);

    void delete(long id);

    void update(Teachar t);
}
