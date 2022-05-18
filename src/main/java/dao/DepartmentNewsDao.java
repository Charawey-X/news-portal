package dao;

import models.DepartmentNews;

import java.util.List;

public interface DepartmentNewsDao {

    void add (DepartmentNews news);


    List<DepartmentNews> getAll();
    DepartmentNews findById(int id);

    void update(int id, String content, String author, int departmentId);

    void deleteById(int id);

    void clearAll();
}
