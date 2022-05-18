package dao;

import models.Employee;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oUserDao implements EmployeeDao{
    private final Sql2o sql2o;

    public Sql2oUserDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Employee employee) {
        String sql = "INSERT INTO employees (name, role, departmentId) VALUES (:name, :role, :departmentId)";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(employee)
                    .executeUpdate()
                    .getKey();
            employee.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employees";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .executeAndFetch(Employee.class);
        }
    }

    @Override
    public Employee findById(int id) {
        String sql = "SELECT * FROM employees WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .executeAndFetchFirst(Employee.class);
        }
    }

    public void update(int id, String name, String role, int departmentId) {
        String sql = "UPDATE employees SET (name, role, departmentid) = (:name, :role, :departmentId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("role", role)
                    .addParameter("departmentId", departmentId)
                    .executeUpdate();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM employees WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE FROM employees";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}