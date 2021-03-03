package com.yl1.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import javax.xml.transform.Source;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author candk
 * @Description
 * @date 3/3/21 - 10:01 AM
 */
public class JDBCUtils {

    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloC3P0");

    /**
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        Connection conn = cpds.getConnection();

        return conn;
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

    /**
     * use druid
     */
    private static DataSource source;

    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

            pros.load(is);
            source = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection2() throws SQLException {
        Connection conn = source.getConnection();
        return conn;
    }
}
