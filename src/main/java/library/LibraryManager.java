package library;
import java.util.ArrayList;

public class LibraryManager {

    ArrayList<String> availableBooks = new ArrayList<>();
    ArrayList<String> borrowedBooks = new ArrayList<>();

    public LibraryManager() {
        availableBooks = new ArrayList<>();
        borrowedBooks = new ArrayList<>();

        availableBooks.add("Harry Potter");
        availableBooks.add("Dune");
        availableBooks.add("The Hobbit");
        availableBooks.add("1984");
    }

    public String searchBook(String title) {
        if (availableBooks.contains(title)) {
            return title + " is available.";
        } else if (borrowedBooks.contains(title)) {
            return title + " is currently borrowed.";
        } else {
            return title + " was not found.";
        }
    }

    public synchronized String borrowBook(String title) {
        if (availableBooks.contains(title)) {
            availableBooks.remove(title);
            borrowedBooks.add(title);

            return title + " has been borrowed successfully.";
        } else {
            return title + " is not available.";
        }
    }

    public synchronized String returnBook(String title) {
        if (borrowedBooks.contains(title)) {
            borrowedBooks.remove(title);
            availableBooks.add(title);

            return title + " has been returned successfully.";
        } else {
            return "Cannot return " + title + " because it is not borrowed.";
        }
    }
}
