package dao;

import models.Department;
import models.DepartmentNews;
import models.Employee;

import java.util.List;

public interface DepartmentDao {


    void add(Department department);

    List<Department> getAll();

    Department findById(int id);

    void update(int id, Department department);

    void deleteById(int id);

    void clearAll();

    List<Employee> getAllEmployeesByDepartment(int departmentId);
    List<DepartmentNews> getAllNewsByDepartment (int departmentId);
}