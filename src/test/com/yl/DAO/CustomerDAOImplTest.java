package test.com.yl.DAO; 

import com.yl.DAO.CustomerDAOImpl;
import com.yl.bean.Customer;
import com.yl1.util.JDBCUtils;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/** 
* CustomerDAOImpl Tester. 
* 
* @author <Authors name> 
* @since <pre>Mar 2, 2021</pre> 
* @version 1.0 
*/ 
public class CustomerDAOImplTest {

   private CustomerDAOImpl dao =  new CustomerDAOImpl();

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: insert(Connection conn, Customer cus) 
* 
*/ 
@Test
public void testInsert() throws Exception { 
//TODO: Test goes here...

    Connection conn = JDBCUtils.getConnection2();
    Customer cus = new Customer(100, "Lily", "lily@163.com", new Date(477535353577L));
    dao.insert(conn, cus);
    System.out.println("insert successfully!");

    JDBCUtils.closeResource(conn, null);

} 

/** 
* 
* Method: deleteById(Connection conn, int id) 
* 
*/ 
@Test
public void testDeleteById() throws Exception { 
//TODO: Test goes here...
    Connection conn = JDBCUtils.getConnection();

    dao.deleteById(conn, 21);
    System.out.println("delete successfully!");

    JDBCUtils.closeResource(conn, null);
} 

/** 
* 
* Method: update(Connection conn, Customer cus) 
* 
*/ 
@Test
public void testUpdate() throws Exception { 
//TODO: Test goes here...

    Connection conn = JDBCUtils.getConnection();
    Customer cus = new Customer(20, "Lily", "lily@163.com", new Date(477535353577L));
    dao.update(conn, cus);
    System.out.println("update successfully!");

    JDBCUtils.closeResource(conn, null);
} 

/** 
* 
* Method: getCustomerById(Connection conn, int id) 
* 
*/ 
@Test
public void testGetCustomerById() throws Exception { 
//TODO: Test goes here...

    Connection conn = JDBCUtils.getConnection();
    Customer cus = dao.getCustomerById(conn, 20);
    System.out.println("get successfully!");
    System.out.println(cus);

    JDBCUtils.closeResource(conn, null);
} 

/** 
* 
* Method: getAll(Connection conn) 
* 
*/ 
@Test
public void testGetAll() throws Exception { 
//TODO: Test goes here...

    Connection conn = JDBCUtils.getConnection();
    List<Customer> all = dao.getAll(conn);
    System.out.println("get successfully!");
    System.out.println(all);

    JDBCUtils.closeResource(conn, null);
} 

/** 
* 
* Method: getCount(Connection conn) 
* 
*/ 
@Test
public void testGetCount() throws Exception { 
//TODO: Test goes here...

    Connection conn = JDBCUtils.getConnection();
    Long count = dao.getCount(conn);
    System.out.println("get successfully!");
    System.out.println(count);

    JDBCUtils.closeResource(conn, null);
} 

/** 
* 
* Method: getMaxBirth(Connection conn) 
* 
*/ 
@Test
public void testGetMaxBirth() throws Exception { 
//TODO: Test goes here...

    Connection conn = JDBCUtils.getConnection();
    Date maxBirth = dao.getMaxBirth(conn);
    System.out.println("get successfully!");
    System.out.println(maxBirth);

    JDBCUtils.closeResource(conn, null);
} 


} 
