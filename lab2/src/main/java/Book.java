import java.sql.Date;

public class Book {
    private long id;
    private String name;
    private String author;
    private int price;
    private Date date;

    public Book(long id,String name, String author, int price, Date date) {
        this.id=id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
