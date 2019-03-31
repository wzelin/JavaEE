package com.xxx.javaee.dao;


import com.xxx.javaee.jdbc.JDBCUtil;
import com.xxx.javaee.jdbc.ObjectUtil;
import com.xxx.javaee.jdbc.SQL;
import com.xxx.javaee.bean.Info;
import com.xxx.javaee.bean.param.InfoQueryParam;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InfoDAOImpl implements InfoDAO {


    @Override
    public List<Info> quemy() {
        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;
        List<Info> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT u.* FROM user_info u ORDER BY u.id DESC;\n";
            psd = conn.prepareStatement(sql);
            rs = psd.executeQuery();
            while (rs.next()) {
                Info info = ObjectUtil.getInstance(rs, Info.class);
                list.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, psd, rs);
            return list;
        }
    }

    @Override
    public List<Info> quemy(InfoQueryParam info) {
        String sql = new SQL() {{
            SELECT("u.*");
            FROM("user_info u");
            if (info.getName().trim().length() > 1) {
                WHERE("u.name like ?");
            }
        }}.toString();
        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;
        List<Info> list = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            psd = conn.prepareStatement(sql);

            if (info.getName().trim().length() > 1) {
                psd.setString(1,info.getName());
            }
            rs = psd.executeQuery();
            while (rs.next()) {
                Info s = ObjectUtil.getInstance(rs, Info.class);
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.close(conn, psd, rs);
            return list;
        }
    }
}