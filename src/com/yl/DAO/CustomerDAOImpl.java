package com.yl.DAO;

import com.yl.bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author candk
 * @Description
 * @date 3/2/21 - 1:51 PM
 */
public class CustomerDAOImpl extends BaseDAO implements CustomerDAO{

    @Override
    public void insert(Connection conn, Customer cus) {

        String sql = "insert into customers(name, email, birth) values(?,?,?)";
        update(conn, sql,cus.getName(), cus.getEmail(), cus.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int id) {

        String sql = "delete from customers where id = ?";
        update(conn, sql, id);
    }

    /**
     *
     * @param conn
     * @param cus
     */
    @Override
    public void update(Connection conn, Customer cus) {

        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        update(conn, sql, cus.getName(), cus.getEmail(), cus.getBirth(), cus.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {

        String sql = "select id, name, email, birth from customers where id = ?";
        Customer customer = getInstance(conn, Customer.class, sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection conn) {

        String sql = "select id, name, email, birth from customers";
        ArrayList<Customer> customers = getInstances(conn, Customer.class, sql);
        return customers;
    }

    @Override
    public Long getCount(Connection conn) {

        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {

        String sql = "select max(birth) from customers";
        return getValue(conn, sql);
    }
}
