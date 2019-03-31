package com.xxx.javaee.service;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.bean.param.StuParam;
import com.xxx.javaee.dao.StudentDAO;
import com.xxx.javaee.dao.StudentDAOImpl;

import java.util.List;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
    private StudentDAO studentDAO = new StudentDAOImpl();
    @Override
    public void insert(Student student) {
        studentDAO.insert(student);
    }

    @Override
    public List<Student> quemy(Map<String, Object> mapwhere) {
        return studentDAO.quemy(mapwhere);
    }

    @Override
    public void delete(long id) {
        studentDAO.delete(id);
    }

    @Override
    public List<Student> quemy(StuParam sp) {
        return studentDAO.quemy(sp);
    }

    @Override
    public void edit(Student student) {
        studentDAO.edit(student);
    }
}
