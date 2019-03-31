package com.xxx.javaee.service;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.bean.param.StuParam;

import java.util.List;
import java.util.Map;

public interface StudentService {
    void insert(Student student);
    List<Student> quemy(Map<String,Object> mapwhere);
    void delete(long id);
    List<Student> quemy(StuParam sp);
    void edit(Student student);
}
