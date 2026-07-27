package library;
import java.util.ArrayList;

public class LibraryManager {

    ArrayList<Book> availableBooks = new ArrayList<>();
    ArrayList<Book> borrowedBooks = new ArrayList<>();

    public LibraryManager() {
        availableBooks = new ArrayList<>();
        borrowedBooks = new ArrayList<>();

        availableBooks.add(new Book("Dune", "Frank Herbert", "111", 1965));
        availableBooks.add(new Book("1984", "George Orwell", "222", 1949));
        availableBooks.add(new Book("The Hobbit", "J.R.R. Tolkien", "333", 1937));
    }

    public String searchBook(String title) {
        /*for (Book book : availableBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return "Book available: " + book.getTitle() + " by " + book.getAuthor() + ", ISBN: " + book.getIsbn();
            }
        }
        return "Book not found."; */

        Book book = findBookByTitle(availableBooks, title);

        if (book != null) {
            return book.getTitle() + " by: " + book.getAuthor() + ", (" + book.getPublicationYear() + ") - ISBN:" + book.getIsbn() + "- Status: Available";
        }

        book = findBookByTitle(borrowedBooks, title);

        if (book != null) {
            return book.getTitle() + " by: " + book.getAuthor() + ", (" + book.getPublicationYear() + ") - ISBN:" + book.getIsbn() + " - Status: Borrowed";
        }

        return "Book not found.";
    }

    public synchronized String borrowBook(String title) {
        Book bookToBorrow = findBookByTitle(availableBooks, title);
        //Book bookToBorrow = null;

        //for (Book book : availableBooks) {
            //if (book.getTitle().equalsIgnoreCase(title)) {
                //bookToBorrow = book;
                //break;
            //}
        //}
        if (bookToBorrow == null) {
            return "Book not found or unavailable.";
        }
        /*if (availableBooks.contains(title)) {
            availableBooks.remove(title);
            borrowedBooks.add(title);

            return title + " has been borrowed successfully.";
        } else {
            return title + " is not available.";
        }*/
        borrowedBooks.add(bookToBorrow);
        availableBooks.remove(bookToBorrow);
        //bookToBorrow.setAvailable(false);
        bookToBorrow.borrow();

        return "Book borrowed successfully: " + bookToBorrow.getTitle();
    }

    public synchronized String returnBook(String title) {
        /*if (borrowedBooks.contains(title)) {
            borrowedBooks.remove(title);
            availableBooks.add(title);

            return title + " has been returned successfully.";
        } else {
            return "Cannot return " + title + " because it is not borrowed.";
        */
        //Book bookToReturn = null;

        /*for (Book book : borrowedBooks) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                bookToReturn = book;
                break;
            }
        }*/
        Book bookToReturn = findBookByTitle(borrowedBooks, title);

        if (bookToReturn == null) {
            return "Book not found in borrowed books.";
        }

        availableBooks.add(bookToReturn);
        borrowedBooks.remove(bookToReturn);
        //bookToReturn.setAvailable(true);
        bookToReturn.returnBook();

        return "Book returned successfully: " + bookToReturn.getTitle();
    }

    private Book findBookByTitle(ArrayList<Book> books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }

        return null;
    }
}
