package com.yl.DAO;

import com.yl.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * @author candk
 * @Description
 * @date 3/2/21 - 1:44 PM
 */
public interface CustomerDAO {

    /**
     *
     * @param conn
     * @param cus
     */
    void insert(Connection conn, Customer cus);

    /**
     *
     * @param conn
     * @param id
     */
    void deleteById(Connection conn, int id);

    /**
     *
     * @param conn
     * @param cus
     */
    void update(Connection conn, Customer cus);

    /**
     *
     * @param conn
     * @param id
     */
    Customer getCustomerById(Connection conn, int id);

    /**
     *
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     *
     * @param conn
     * @return
     */
    Long getCount(Connection conn);

    /**
     *
     * @param conn
     * @return
     */
    Date getMaxBirth(Connection conn);
}
