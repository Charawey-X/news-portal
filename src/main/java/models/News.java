package models;


import java.util.Objects;

public class News {
    public int id;
    public String content;
    public String author;

    private final static String DATABASETYPE = "News";

    public News(String content, String author) {
        this.content = content;
        this.author = author;
    }
}