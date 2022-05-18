package dao;

import models.Department;
import models.News;
import org.junit.*;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Sql2oNewsDaoTest {
    String connectionString = "jdbc:postgresql://localhost:5432/news_portal_test";
    Sql2o sql2o = new Sql2o(connectionString, "x", "230620");
    private Connection conn;
    private Sql2oEmployeeDao employeeDao;
    private Sql2oDepartmentDao departmentDao;
    private Sql2oNewsDao newsDao;

    @Before
    public void setUp() throws Exception {
        conn = sql2o.open();
        employeeDao = new Sql2oEmployeeDao(sql2o);
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void add() {
        News news = setupNews();
        News news1 = newsDao.findById(news.getId());
        assertEquals(news.getId(), news1.getId());
    }

    @Test
    public void findAll() {
        newsDao.clearAll();
        News news1 = setupNews();
        News news2 = setupNews();
        assertEquals(2, newsDao.getAll().size());
    }

    @Test
    public void findById() {
        News news = setupNews();
        News foundNews = newsDao.findById(news.getId());
        assertEquals(news, foundNews);
    }

    @Test
    public void update() {
        News news = setupNews();
        News updatedNews = new News("Am the updated headline", "I am a very interesting content");
        updatedNews.setId(news.getId());
        newsDao.update(news.getId(), updatedNews);
        assertEquals(updatedNews.getContent(), "Am the updated headline");
    }

    @Test
    public void deleteById() {
        News news = setupNews();
        newsDao.deleteById(news.getId());
        assertEquals(null, newsDao.findById(news.getId()));
    }


    @Test
    public void clearAll() {
        newsDao.clearAll();
        assertEquals(0, newsDao.getAll().size());
    }

    // helpers
    public News setupNews() {
        Department department = new Department("ICT", "ICT support services", 20);
        departmentDao.add(department);
        News news = new News("Am the headline", "I am a very interesting content");
        newsDao.add(news);
        return news;
    }
}
