package com.yl.preparedstatement.crud;

import com.yl.bean.User;
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



    @Test
    public void transactionTest() throws Exception {

        Connection conn = JDBCUtils.getConnection();
        String sql = "select user, password, balance from user_table where user = ?";
        ArrayList<User> user = getInstances(conn, User.class, sql, "CC");

        System.out.println(user);

    }

    @Test
    public void transactionUpdateTest() throws Exception {
        Connection conn = JDBCUtils.getConnection();

        String sql = "update user_table set balance = ? where user = ?";
        update(conn, sql, 5000, "CC");
    }

    @Test
    public void test1() throws Exception {
        Connection conn = JDBCUtils.getConnection();
        System.out.println(conn.getTransactionIsolation());
    }

    /**
     *
     * @param conn
     * @param sql
     * @param args
     * @return
     */
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

    /**
     *
     * @param conn
     * @param clazz
     * @param sql
     * @param args
     * @param <T>
     * @return
     * @throws Exception
     */
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
