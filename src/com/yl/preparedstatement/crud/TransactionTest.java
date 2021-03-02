package com.yl.preparedstatement.crud;

import com.yl.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

/**
 * @author candk
 * @Description
 * @date 3/2/21 - 9:34 AM
 */
public class TransactionTest {

    @Test
    public void testTransaction() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        conn.setAutoCommit(false);

        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(conn, sql1, "AA");

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(conn, sql2, "BB");

        conn.commit();
        System.out.println("Transaction successful!");

        JDBCUtils.closeResource(conn,null);
    }

    public static int update(Connection conn, String sql, Object ...args) {

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeResource(null, ps);
        }

        return 0;
    }

    @Test
    public void TransactionTest() throws Exception {

        Connection conn = JDBCUtils.getConnection();

    }

    //session
    public static <T> ArrayList<T> getInstances(Connection conn, Class<T> clazz, String sql, Object ...args) throws Exception {

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

        JDBCUtils.closeResource(null, ps, rs);

        return al;
    }


}
