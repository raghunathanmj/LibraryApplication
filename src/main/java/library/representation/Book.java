package library.representation;

/**
 * Created by raghunathan.mj on 22/07/15.
 */


public class Book {
    private final int isbn; //Unique
    private final String title;

    public Book() {
        isbn = -1;
        title = null;
    }

    public Book(int isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }
    public int getIsbn() {return isbn;}
    public String getTitle() {return title;}
}

