package library;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean available;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }
    public String getIsbn() {
        return isbn;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public boolean isAvailable() {
        return available;
    }

    /*public void setAvailable(boolean available) {
        this.available = available;
    }*/
    public void borrow() {
        available = false;
    }

    public void returnBook() {
        available = true;
    }
}
