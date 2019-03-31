package com.xxx.javaee.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbsConnectionBuilder
        implements ConnectionBuilder {
    // 一般在这个抽象类中定义各个实现类共有的方法

    public void close(Connection conn){
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close(Statement stat) {
        if(stat != null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close(ResultSet rs) {
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close(Connection conn, Statement stat, ResultSet rs) {
        close(rs);
        close(stat);
        close(conn);
    }
}
