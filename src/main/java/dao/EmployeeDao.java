package dao;

import models.Employee;

import java.util.List;

public interface EmployeeDao {
    void add(Employee employee);

    List<Employee> getAll();

    Employee findById(int id);

    void deleteById(int id);

    void clearAll();
}
