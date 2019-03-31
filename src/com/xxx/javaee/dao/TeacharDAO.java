package com.xxx.javaee.dao;


import com.xxx.javaee.bean.Teachar;
import com.xxx.javaee.bean.param.Pager;
import com.xxx.javaee.bean.param.TeaParam;


public interface TeacharDAO {
    void intsert(Teachar teachar);

    void delete(long id);

    Pager<Teachar> query(TeaParam param, Pager<Teachar> pager);

    void update(Teachar t);
}
