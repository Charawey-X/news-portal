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


    public static String getDATABASETYPE() {
        return DATABASETYPE;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return id == news.id &&
                Objects.equals(content, news.content) &&
                Objects.equals(author, news.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, author);
    }

    public void setId(int id) {
        this.id = id;
    }
}
