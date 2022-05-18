package models;

import java.util.Objects;


public class Employee {
    private int id;
    private String name;
    private String role;
    private int departmentId;

    public Employee(String name, String role, int departmentId) {
        this.name = name;
        this.role = role;
        this.departmentId = departmentId;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                departmentId == employee.departmentId &&
                Objects.equals(name, employee.name) &&
                Objects.equals(role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, role, departmentId);
    }

    public void setId(int id) {
        this.id = id;
    }
}