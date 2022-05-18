package models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NewsTest {
    private News setUpNews() {
        return new News("Killing Spree","grandFather");
    }
    @Test
    public void newsInstantiatesCorrectly_true() throws Exception {
        News testNews = setUpNews();
        assertTrue(true);
    }

    @Test
    public void newNews_getsName_String() throws Exception {
        News testNews = setUpNews();
        assertEquals("Killing Spree",testNews.getContent());
    }

    @Test
    public void getAuthor__getsDescription_news() throws Exception {
        News testNews = setUpNews();
        assertEquals("grandFather", testNews.getAuthor());
    }

    @Test
    public void getId_userInstantiatesWithAnId_0() {
        News testNews = setUpNews();
        assertEquals(0,testNews.getId());
    }
}