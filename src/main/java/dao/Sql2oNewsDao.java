package dao;

import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oNewsDao implements NewsDao {
    private final Sql2o sql2o;

    public Sql2oNewsDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(News news) {
        String sql = "INSERT INTO news (content, author, type) VALUES (:content, :author, :DATABASETYPE);";
        try (Connection conn = sql2o.open()) {
            int id = (int) conn.createQuery(sql, true)
                    .bind(news)
                    .addParameter("DATABASETYPE", News.getDATABASETYPE())
                    .executeUpdate()
                    .getKey();
            news.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<News> getAll() {
        String sql = "SELECT * FROM news WHERE type = :type;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("type", News.getDATABASETYPE())
                    .throwOnMappingFailure(false)
                    .executeAndFetch(News.class);
        }
    }

    @Override
    public News findById(int id) {
        String sql = "SELECT * FROM news WHERE id = :id;";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(News.class);
        }
    }


    public void update(int id, String content, String author) {
        String sql = "UPDATE news SET (content, author) = (:content, :author) WHERE id = :id; ";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("content", content)
                    .addParameter("author", author)
                    .addParameter("id", id)
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