package com.yl.preparedstatement.crud;


import com.yl.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;


/**
 * @author candk
 * @date 2/27/21 - 7:52 PM
 */

public class PreparedStatementUpdateTest {

    @Test
    public void updateTest() throws Exception {

        Connection conn = JDBCUtils.getConnection();

        String sql = "update customers set name = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, "贝多芬");
        ps.setObject(2, 18);

        ps.execute();

        JDBCUtils.closeResource(conn, ps);
    }

    @Test
    public void update(String sql, Object ...args) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1, args[i]);
            }

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }

    @Test
    public void commonUpdateTest() {
        String sql = "update customers set name = ? where id = ?";
        update(sql, "贝多芬", 18);
    }

    @Test
    public void insertTest() {


    }
}
