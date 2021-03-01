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
 * @date 2/28/21 - 11:01 AM
 */
public class CustomerForQuery {

    @Test
    public void testQuery1() throws Exception {
        Connection conn = JDBCUtils.getConnection();

        String sql = "select id, name,email, birth from customers where id < 10";
        PreparedStatement ps = conn.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            Date birth = rs.getDate(4);

            System.out.println(new Customer(id, name, email, birth));
        }

        JDBCUtils.closeResource(conn, ps, rs);
    }

    public static ArrayList customerQuery(String sql, Object ...args) {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            ArrayList<Customer> al = new ArrayList<>();

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            System.out.println(rs);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();


            while (rs.next()) {

                Customer cus = new Customer();

                for (int i = 0; i < columnCount; i++) {
                    Object value = rs.getObject(i + 1);
                    String columnName = rsmd.getColumnLabel(i + 1);
                    Field field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(cus, value);
                }

                al.add(cus);
            }

            return al;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, rs);
        }
        return null;
    }

    @Test
    public void testCustomerQuery() {
        String sql = "select id, name, email, birth from customers where id < ?";
        ArrayList al = customerQuery(sql, 6);
        System.out.println(al);
    }


}
