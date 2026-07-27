package library;

public class BookNotFoundException extends Exception{
    public BookNotFoundException(String title) {
        super("Book not found: " + title);
    }
}
