package com.yl.exer;

import com.yl.util.JDBCUtils;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.Scanner;

/**
 * @author candk
 * @date 3/1/21 - 2:14 PM
 */
public class Exer1 {

    public static boolean update(String sql, Object ...args) throws Exception {

        Connection conn = JDBCUtils.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            ps.setObject(i + 1, args[i]);
        }

        int res = ps.executeUpdate();
        JDBCUtils.closeResource(conn, ps);

        return (res > 0);

    }


    @Test
    public void test1() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input the Customer name:");
        String name = scanner.nextLine();

        System.out.print("input the Customer email:");
        String email = scanner.nextLine();

        System.out.print("input the Customer birth:");
        String birth = scanner.nextLine();

        String sql = "insert into customers(name, email, birth) values(?,?,?)";

        if (update(sql, name, email, birth)) {
            System.out.println("Update successful.");
        } else {
            System.out.println("Update fail.");
        }
    }
}
