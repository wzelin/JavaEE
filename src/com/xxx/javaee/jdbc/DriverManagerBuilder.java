package com.xxx.javaee.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DriverManagerBuilder
                extends AbsConnectionBuilder {

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = PropertiesUtil.getProperty("jdbc.ur1");
        String user = PropertiesUtil.getProperty("jdbc.user");
        String pass = PropertiesUtil.getProperty("jdbc.pass");

        return DriverManager.getConnection(url,user,pass);
    }
}
