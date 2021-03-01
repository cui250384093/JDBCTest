package com.yl.preparedstatement.crud;

import com.yl.bean.Customer;
import com.yl.bean.Order;
import com.yl.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author candk
 * @date 3/1/21 - 10:16 AM
 */
public class PreparedStatementQueryTest {

    public static <T> ArrayList<T> getInstances(Class<T> clazz, String sql, Object ...args) throws Exception {

        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }

        ResultSet rs = ps.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        ArrayList<T> al =new ArrayList<T>();

        while (rs.next()) {
            T t = clazz.getDeclaredConstructor().newInstance();
            for (int i = 0; i < columnCount; i++) {
                Object columnValue = rs.getObject(i + 1);
                String columnName = rsmd.getColumnLabel(i + 1);

                Field field = clazz.getDeclaredField(columnName);
                field.setAccessible(true);
                field.set(t, columnValue);
            }

            al.add(t);
        }

        JDBCUtils.closeResource(conn, ps, rs);

        return al;
    }

    @Test
    public void testGetInstances() throws Exception {
        String sql = "select id, name, email from customers where id < ?";
        ArrayList<Customer> al1 = getInstances(Customer.class, sql, 11);
        al1.forEach(System.out::println);

        System.out.println();

        String sql2 = "select order_id orderId, order_name orderName from `order` where order_id < ?";
        ArrayList<Order> al2 = getInstances(Order.class, sql2, 10);
        al2.forEach(System.out::println);
    }
}
