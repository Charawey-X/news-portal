package dao;

import models.DepartmentNews;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oDepartmentNewsDao implements DepartmentNewsDao {
    private final Sql2o sql2o;

    public Sql2oDepartmentNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }
    @Override
    public void add(DepartmentNews news) {
        String sql = "INSERT INTO news (content, author, departmentid, type) VALUES (:content, :author, :departmentId, :DATABASETYPE);";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(news)
                    .addParameter("DATABASETYPE", DepartmentNews.getDATABASETYPE())
                    .executeUpdate()
                    .getKey();
            news.setDepartmentId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<DepartmentNews> getAll() {
        String sql = "SELECT * FROM news WHERE type = :type;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .throwOnMappingFailure(false)
                    .addParameter("type", DepartmentNews.getDATABASETYPE())
                    .executeAndFetch(DepartmentNews.class);
        }
    }

    @Override
    public DepartmentNews findById(int id) {
        String sql = "SELECT * FROM news WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(DepartmentNews.class);
        }
    }

    @Override
    public void update(int id, String content, String author, int departmentId) {
        String sql = "UPDATE news SET (content, author, departmentid) = (:content, :author, :departmentId) ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("content", content)
                    .addParameter("author", author)
                    .addParameter("departmentId", departmentId)
                    .executeUpdate();
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM news WHERE id = :id ;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
    @Override
    public void clearAll() {
        String sql = "DELETE FROM news;";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        }
    }
}
