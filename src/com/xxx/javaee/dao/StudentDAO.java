package com.xxx.javaee.dao;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.bean.param.StuParam;

import java.util.List;
import java.util.Map;

public interface StudentDAO {
//添加
    int insert(Student student);
//查找
    List<Student> quemy(Map<String,Object> map);
//删除
    int delete(long id);
    //查找
    List<Student> quemy(StuParam sp);

    void edit(Student student);
}
