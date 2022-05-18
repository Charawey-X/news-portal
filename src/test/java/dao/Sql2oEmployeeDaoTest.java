package dao;

import models.Department;
import models.Employee;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sql2oEmployeeDaoTest {

    String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
    Sql2o sql2o = new Sql2o(connectionString, "x", "230620");
    private Connection conn;
    private Sql2oEmployeeDao employeeDao;
    private Sql2oDepartmentDao departmentDao;

    @Before
    public void setUp() throws Exception {
        conn = sql2o.open();
        employeeDao = new Sql2oEmployeeDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        Employee testUser = setupUser();
        assertEquals(testUser.getId(), testUser.getId());
    }

    @Test
    public void findAll() {
        employeeDao.clearAll();
        Employee user1 = setupUser();
        Employee user2 = setupUser();
        assertEquals(2, employeeDao.getAll().size());
    }

    @Test
    public void findById() {
        Employee user = setupUser();
        Employee foundUser = employeeDao.findById(user.getId());
        assertEquals(user, foundUser);
    }

    @Test
    public void update() {
        Employee user = setupUser();
        Employee updatedUser = new Employee("Jackie Chan", "Manager", 1);
        updatedUser.setId(user.getId());
        employeeDao.update(user.getId(), updatedUser);
        assertEquals(user.getId(), updatedUser.getId());
    }

    @Test
    public void deleteById() {
        Employee user = setupUser();
        employeeDao.deleteById(user.getId());
        assertEquals(null, employeeDao.findById(user.getId()));
    }

    @Test
    public void clearAll() {
        employeeDao.clearAll();
        assertEquals(0, employeeDao.getAll().size());
    }

    // helpers
    public Employee setupUser() {
        Department department = new Department("ICT", "ICT support services", 20);
        departmentDao.add(department);
        System.out.println(department);
        Employee user = new Employee("Jackie", "Manager", 1);
        employeeDao.add(user);
        return user;
    }
}
