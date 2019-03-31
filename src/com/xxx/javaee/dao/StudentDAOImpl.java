package com.xxx.javaee.dao;

import com.xxx.javaee.bean.Student;
import com.xxx.javaee.jdbc.JDBCUtil;
import com.xxx.javaee.jdbc.ObjectUtil;
import com.xxx.javaee.bean.param.StuParam;
import com.xxx.javaee.jdbc.SQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentDAOImpl implements StudentDAO {

    public int insert(Student student) {
        Connection conn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "INSERT INTO tab_student (`name`,age,sex,favs,edu,`desc`)" +
                    " VALUES (?,?,?,?,?,?)";
            pst = conn.prepareStatement(sql);
            pst.setString(1, student.getName());
            pst.setInt(2, student.getAge());
            pst.setInt(3, student.getSex());
            pst.setString(4, student.getFavs());
            pst.setInt(5, student.getEdu());
            pst.setString(6, student.getDesc());
            rs = pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            JDBCUtil.close(conn, pst, null);
            return rs;
        }
    }

    @Override
    public List<Student> quemy(Map<String, Object> wheremap) {
        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM tab_student";
            psd = conn.prepareStatement(sql);
            rs = psd.executeQuery();
            while (rs.next()) {
                Student student = ObjectUtil.getInstance(rs, Student.class);
                list.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, psd, rs);
            return list;
        }
    }

    @Override
    public int delete(long id) {
        Connection conn = null;
        PreparedStatement pst = null;
        int rs = 0;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "DELETE FROM tab_student WHERE id = ?;";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            rs = pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            JDBCUtil.close(conn, pst, null);
            return rs;
        }
    }

    @Override
    public List<Student> quemy(StuParam sp) {
        String sql = new SQL() {{
            SELECT("s.*");
            FROM("tab_student s");
            if (sp.getName().trim().length() > 1) {
                WHERE("s.name like ?");
            }
            if (sp.getStart() != null) {
                WHERE("s.age >= ?");
            }
            if (sp.getEnd() != null) {
                WHERE("s.age <= ?");
            }
        }}.toString();
        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;
        List<Student> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            psd = conn.prepareStatement(sql);
            int index = 1;
            if (sp.getName().trim().length() > 1) {
                psd.setString(index++, sp.getName());
            }
            if (sp.getStart() != null) {
                psd.setInt(index++, sp.getStart());
            }
            if (sp.getEnd() != null) {
                psd.setInt(index++, sp.getEnd());
            }

            rs = psd.executeQuery();
            while (rs.next()) {
                Student s = ObjectUtil.getInstance(rs, Student.class);
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, psd, rs);
            return list;
        }
    }

    @Override
    public void edit(Student student) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = JDBCUtil.getConnection();
            conn.setAutoCommit(false);
            String sql = "UPDATE tab_student SET `name` = ?,age = ?,sex = ?,favs = ?,edu = ?,`desc` = ? WHERE id = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, student.getName());
            pst.setInt(2, student.getAge());
            pst.setInt(3, student.getSex());
            pst.setString(4, student.getFavs());
            pst.setInt(5, student.getEdu());
            pst.setString(6, student.getDesc());
            pst.setLong(7, student.getId());
             pst.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            JDBCUtil.close(conn, pst, null);
        }
    }
}