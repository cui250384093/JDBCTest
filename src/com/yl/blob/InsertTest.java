package com.yl.blob;

import com.yl.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;

/**
 * @author candk
 * @Description
 * @date 3/2/21 - 8:47 AM
 */
public class InsertTest {

    @Test
    public void batchInsert1() throws Exception {

        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into goods(name) values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < 100; i++) {
            ps.setObject(1, "name_" + i);
            ps.execute();
        }

        JDBCUtils.closeResource(conn,ps);
    }

    @Test
    public void batchInsert2() throws Exception {

        Connection conn = JDBCUtils.getConnection();
        String sql = "insert into goods(name) values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i <= 10000; i++) {
            ps.setObject(1, "name_" + i);

            ps.addBatch();

            if (i % 500 == 0) {
                ps.executeBatch();
                ps.clearBatch();
            }
        }

        JDBCUtils.closeResource(conn,ps);
    }

    @Test
    public void batchInsert3() throws Exception {

        Connection conn = JDBCUtils.getConnection();

         conn.setAutoCommit(false);

        String sql = "insert into goods(name) values(?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < 100000; i++) {
            ps.setObject(1, "name_" + i);

            ps.addBatch();

            if (i % 500 == 0) {

                ps.executeBatch();
                ps.clearBatch();
            }

        }

        JDBCUtils.closeResource(conn, ps);
    }
}
