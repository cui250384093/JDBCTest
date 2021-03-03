package com.yl1.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @author candk
 * @Description
 * @date 3/3/21 - 9:15 AM
 */
public class C3P0Test {

    @Test
    public void testGetConnection() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver");
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        cpds.setUser("root");
        cpds.setPassword("lili");

        cpds.setInitialPoolSize(10);
        cpds.setMaxPoolSize(100);

        Connection conn = cpds.getConnection();
        System.out.println(conn);
    }

    @Test
    public void testGetConnection1() {
        ComboPooledDataSource cpds = new ComboPooledDataSource("helloC3P0");
        System.out.println(cpds);

    }
}
