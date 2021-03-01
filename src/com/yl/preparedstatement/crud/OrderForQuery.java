package com.yl.preparedstatement.crud;

import com.yl.bean.Order;
import com.yl.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author candk
 * @date 3/1/21 - 9:48 AM
 */
public class OrderForQuery {

    @Test
    public void testerQuery() throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();

            String sql = "select order_id, order_name, order_date from `order` where order_id = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1, 1);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = (int) rs.getObject(1);
                String name = (String) rs.getObject(2);
                Date date = (Date) rs.getObject(3);

                Order order = new Order(id, name, date);
                System.out.println(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
    }

    public ArrayList orderForQuery(String sql, Object ...args) throws Exception {

        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        ArrayList al =new ArrayList();

        while (rs.next()) {
            Order order = new Order();
            for (int i = 0; i < columnCount; i++) {
                Object columnValue = rs.getObject(i + 1);
                String columnName = rsmd.getColumnLabel(i + 1);

                Field field = Order.class.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(order, columnValue);
            }

            al.add(order);
        }

        return al;
    }

    @Test
    public void testOrderForQuery() throws Exception {
        String sql = "select order_id orderId, order_name orderName, order_date orderDate from `order` where order_id < ?";
        ArrayList al1 = orderForQuery(sql, 10);
        System.out.println(al1);
    }

}
