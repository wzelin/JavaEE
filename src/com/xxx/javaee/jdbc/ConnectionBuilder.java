package com.xxx.javaee.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接创建者
 */
public interface ConnectionBuilder {
    /**
     * 建立连接
     * @return 连接对象
     */
    Connection getConnection()throws SQLException;

    /**
     * 关闭连接
     * @param conn
     */
    void close(Connection conn);

    /**
     * 关闭Statement
     * @param stat
     */
    void close(Statement stat);

    /**
     * 关闭ResultSet
     * @param rs
     */
    void close(ResultSet rs);

    /**
     * 关闭所有资源
     * @param conn
     * @param stat
     * @param rs
     */
    void close(Connection conn,Statement stat,ResultSet rs);
}
