package com.xxx.javaee.service;

import com.xxx.javaee.bean.Teachar;
import com.xxx.javaee.bean.param.Pager;
import com.xxx.javaee.bean.param.TeaParam;
import com.xxx.javaee.dao.TeacharDAO;
import com.xxx.javaee.dao.TeacharDAOlmpl;

import java.util.List;
import java.util.Map;

public class TeacharServicelmpl implements TeacharService {
    private TeacharDAO teacharDAO = new TeacharDAOlmpl();

    @Override
    public void insert(Teachar teachar) {
        teacharDAO.intsert(teachar);
    }

    @Override
    public void delete(long id) {
        teacharDAO.delete(id);
    }

    @Override
    public Pager<Teachar> query(TeaParam param, Pager<Teachar> pager) {
        return teacharDAO.query(param,pager);
    }

    @Override
    public void update(Teachar t) {
        teacharDAO.update(t);
    }
}
