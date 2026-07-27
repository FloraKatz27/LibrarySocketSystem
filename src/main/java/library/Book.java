package library;

public class Book {
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    //private boolean available;
    private BookStatus status;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        //this.available = true;
        status = BookStatus.AVAILABLE;
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

    public BookStatus getStatus() {
        return status;
    }
    public boolean isAvailable() {
        //return available;
        return status == BookStatus.AVAILABLE;
    }

    /*public void setAvailable(boolean available) {
        this.available = available;
    }*/
    public void borrow() {
        //available = false;
        status = BookStatus.BORROWED;
    }

    public void returnBook() {
        //available = true;
        status = BookStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return title + " by " + author + " (" + publicationYear + ") - ISBN: " + isbn + " - Status: " + status;
    }
}
