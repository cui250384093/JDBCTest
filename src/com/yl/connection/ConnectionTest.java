package com.yl.connection;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

/**
 * @author candk
 * @date 2/26/21 - 3:32 PM
 */
public class ConnectionTest {

    @Test
    public void testConnection1() throws SQLException {

        Driver driver = new com.mysql.cj.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/test";

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "lili");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    @Test
    public void testConnection2() throws Exception {

        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");

        Driver driver = (Driver)clazz.getDeclaredConstructor().newInstance();

        String url = "jdbc:mysql://localhost:3306/test";

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "lili");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    @Test
    public void testConnection3() throws Exception {

        Class aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.getDeclaredConstructor().newInstance();

        DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "lili";

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    @Test
    public void testConnection4() throws Exception {

        Class aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        //Driver driver = (Driver) aClass.getDeclaredConstructor().newInstance();

        //DriverManager.registerDriver(driver);

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "lili";

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    @Test
    public void testConnection5() throws Exception {

        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties pro = new Properties();
        pro.load(is);

        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driveClass = pro.getProperty("driveClass");

        Class.forName(driveClass);

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }
}
