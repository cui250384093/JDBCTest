package com.yl.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
@author candk
@date   2/28/21 - 9:58 AM

*/

public class JDBCUtils {

    public static Connection getConnection() throws Exception {

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");

        Properties pro = new Properties();
        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driveClass = pro.getProperty("driveClass");

        Class.forName(driveClass);

        return DriverManager.getConnection(url, user, password);
    }

    public static void closeResource(Connection conn, Statement ps) {

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {

        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
