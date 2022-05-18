package models;

import java.util.Objects;

public class DepartmentNews extends News {
    private int departmentId;

    private final static  String DATABASETYPE = "Department news";
    public DepartmentNews(String content, String author, int departmentId) {
        super(content, author);
        this.departmentId= departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public static String getDATABASETYPE() {
        return DATABASETYPE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DepartmentNews that = (DepartmentNews) o;
        return departmentId == that.departmentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departmentId);
    }
}