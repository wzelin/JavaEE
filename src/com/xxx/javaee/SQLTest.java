package com.xxx.javaee;

import com.xxx.javaee.jdbc.JDBCUtil;
import com.xxx.javaee.jdbc.ObjectUtil;
import com.xxx.javaee.jdbc.SQL;
import com.xxx.javaee.bean.Student;
import com.xxx.javaee.bean.param.StuParam;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLTest {

    public static void main(String[] args) {

        StuParam sp = new StuParam();
        sp.setName("%张%");
//        sp.setStart(11);
        sp.setEnd(34);

        String sql = new SQL(){{
            SELECT("s.*");
            FROM("tab_student s");
            if(sp.getName() != null){
                WHERE("s.name like ?");
            }
            if(sp.getStart() != null){
                WHERE("s.age >= ?");
            }
            if(sp.getEnd() != null){
                WHERE("s.age <= ?");
            }

        }}.toString();
        System.out.println(sql);

        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;


        try {
            conn = JDBCUtil.getConnection();
            psd = conn.prepareStatement(sql);

            int index = 1;
            if(sp.getName() != null){
                psd.setString(index++,sp.getName());
            }
            if(sp.getStart() != null){
                psd.setInt(index++,sp.getStart());
            }
            if(sp.getEnd() != null){
                psd.setInt(index++,sp.getEnd());
            }

            rs = psd.executeQuery();
            List<Student> list = new ArrayList<>();
            while (rs.next()){
                Student s = ObjectUtil.getInstance(rs,Student.class);
                list.add(s);
            }

            System.out.println("结果：" + list);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.close(conn,psd,rs);
        }


    }

}
