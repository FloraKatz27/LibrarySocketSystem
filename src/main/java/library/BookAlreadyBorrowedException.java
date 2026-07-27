package library;

public class BookAlreadyBorrowedException extends Exception{
    public BookAlreadyBorrowedException(String title) {
        super("Book is already borrowed: " + title);
    }
}
