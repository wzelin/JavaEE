package com.xxx.javaee.poi;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.util.poi.writer.AbsExcelWriter;
import com.xxx.javaee.util.poi.writer.ExcelWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestWriter {
    public static void main(String[] args) throws Exception {
        ExcelWriter<Student> writer = new AbsExcelWriter<Student>("D:/aaa.xlsx") {
            @Override
            public List<String> resolveBean(Student bean) throws IOException {
               List<String> list = new ArrayList<>();
               list.add(bean.getId()+"");
               list.add(bean.getName());
               list.add(bean.getAge()+"");
               list.add(bean.getSex()+"");
               list.add(bean.getDesc());
                return list;
            }

            @Override
            public List<String> getHeaders() {
                List<String> list = new ArrayList<>();
                list.add("id");
                list.add("姓名");
                list.add("年龄");
                list.add("性别");
                list.add("简介");
                return list;
            }
        };
        List<Student> list = new ArrayList<>();
        Student s = new Student();
        s.setId(1);
        s.setName("吴磊");
        s.setAge(12);
        s.setSex(1);
        s.setDesc("我很帅");
        list.add(s);
        writer.write(list);
    }
}
