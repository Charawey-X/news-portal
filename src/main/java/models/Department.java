package models;

import java.util.Objects;

public class Department {
    private String name;
    private String description;
    private int numberOfEmployees;
    private int id;

    public Department(String name, String description, int numberOfEmployees) {
        this.name=name;
        this.description=description;
        this.numberOfEmployees=numberOfEmployees;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return numberOfEmployees == that.numberOfEmployees &&
                id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, numberOfEmployees, id);
    }

}