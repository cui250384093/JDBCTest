package com.yl1.connection;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import javax.xml.transform.Source;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author candk
 * @Description
 * @date 3/3/21 - 10:32 AM
 */
public class DBCPTest {

    @Test
    public void testConnection() throws SQLException {

        BasicDataSource source = new BasicDataSource();

        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setUsername("root");
        source.setPassword("lili");

        source.setInitialSize(10);
        source.setMaxTotal(20);

        Connection conn = source.getConnection();

        System.out.println(conn);
    }

    @Test
    public void testGetConnection1() throws Exception {

        Properties pros = new Properties();

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
        pros.load(is);

        //create a poll
        BasicDataSource sou = BasicDataSourceFactory.createDataSource(pros);
        Connection conn = sou.getConnection();
        System.out.println(conn);
            }
}
