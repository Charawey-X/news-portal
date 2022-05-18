package dao;

import models.Department;
import models.DepartmentNews;
import models.Employee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentDao implements DepartmentDao{

    private final Sql2o sql2o;

    public Sql2oDepartmentDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Department department) {
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery("INSERT INTO departments (name, description, numberOfEmployees) VALUES (:name, :description, :numberOfEmployees)", true)
                    .bind(department)
                    .executeUpdate()
                    .getKey();
            department.setId(id);
        } catch (Sql2oException exc) {
            System.out.println(exc);
        }
    }

    @Override
    public List<Department> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM departments")
                    .executeAndFetch(Department.class);
        }
    }
    @Override
    public Department findById(int id) {
        String sql = "SELECT * FROM departments WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Department.class);
        }
    }

    @Override
    public void update(int id, Department department) {
        String sql = "UPDATE departments SET (name, description, numberofemployees) = (:name, :description, :numberOfEmployees) ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .bind(department)
                    .executeUpdate();
        }catch (Sql2oException exc) {
            System.out.println(exc);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM departments WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE FROM departments;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }

    @Override
    public List<Employee> getAllEmployeesByDepartment(int departmentId) {
        String sql = "SELECT * FROM employees WHERE departmentid = :departmentId ;";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("departmentId", departmentId)
                    .executeAndFetch(Employee.class);
        }
    }
    @Override
    public List<DepartmentNews> getAllNewsByDepartment(int departmentId) {
        String sql = "SELECT * FROM news WHERE departmentid = :departmentId ;";
        try (Connection conn = sql2o.open()){
            return conn.createQuery(sql)
                    .addParameter("departmentId", departmentId)
                    .throwOnMappingFailure(false)
                    .executeAndFetch(DepartmentNews.class);
        }
    }
}