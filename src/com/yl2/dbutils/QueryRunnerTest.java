package com.yl2.dbutils;

import com.yl.bean.Customer;
import com.yl1.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author candk
 * @Description
 * @date 3/3/21 - 4:05 PM
 */
public class QueryRunnerTest {

    @Test
    public void testInsert() throws Exception {
        QueryRunner qr = new QueryRunner();

        Connection conn = JDBCUtils.getConnection2();
        String sql = "insert into customers(name, email, birth) values(?, ?, ?)";
        int rows = qr.update(conn, sql, "Lucy", "lucy@163.com", "1985-05-14");

        JDBCUtils.closeResource(conn, null);
    }

    @Test
    public void testQuery1() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection conn = JDBCUtils.getConnection2();
        String sql = "select id, name, email, birth from customers where id = ?";
        BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
        Customer cus = runner.query(conn, sql, handler, 22);
        System.out.println(cus);

        JDBCUtils.closeResource(conn, null);
    }
}
