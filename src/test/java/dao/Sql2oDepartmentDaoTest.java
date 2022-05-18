package dao;

import models.Department;
import models.Employee;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sql2oDepartmentDaoTest {

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
        Department department = setupDepartment();
        Department department1 = departmentDao.findById(department.getId());
        assertEquals(department.getId(), department1.getId());
    }

    @Test
    public void findAll() {
        departmentDao.clearAll();
        Department department1 = setupDepartment();
        Department department2 = setupDepartment();
        assertEquals(2, departmentDao.getAll().size());
    }

    @Test
    public void findById() {
        Department department = setupDepartment();
        Department foundDepartment = departmentDao.findById(department.getId());
        assertEquals(department, foundDepartment);
    }

    @Test
    public void findUsersByDepartment() {
        Department department1 = setupDepartment();
        Employee employee = new Employee("Marcus", "Manager", department1.getId());
        employeeDao.add(employee);
        departmentDao.findById(employee.getDepartmentId());
        assertEquals(department1.getId(), employee.getDepartmentId());
    }

    @Test
    public void update() {
        Department department = setupDepartment();
        Department updatedDepartment = new Department("ICT Dept", "ICT support services", 20);
        updatedDepartment.setId(department.getId());
        departmentDao.update(department.getId(), updatedDepartment);
        assertEquals(department.getId(), updatedDepartment.getId());
    }

    @Test
    public void deleteById() {
        Department department = setupDepartment();
        departmentDao.deleteById(department.getId());
        assertEquals(null, departmentDao.findById(department.getId()));
    }

    @Test
    public void clearAll() {
        departmentDao.clearAll();
        assertEquals(0, departmentDao.getAll().size());
    }

    // helpers
    public Department setupDepartment() {
        Department department = new Department("ICT", "ICT support services", 20);
        departmentDao.add(department);
        return department;
    }
}
