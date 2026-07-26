package library;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class LibraryServer {
    public static void main(String[] args) {
        System.out.println("Starting server...");

        ArrayList<String> availableBooks = new ArrayList<>();
        ArrayList<String> borrowedBooks = new ArrayList<>();

        availableBooks.add("Harry Potter");
        availableBooks.add("Dune");
        availableBooks.add("The Hobbit");
        availableBooks.add("1984");

        //ServerSocket serverSocket;
        try {
            ServerSocket serverSocket = new ServerSocket(5001);

            System.out.println("Server is listening on port 5001.");

            System.out.println("Waiting for a client...");

            Socket clientSocket = serverSocket.accept();

            System.out.println("A client has connected.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request = reader.readLine();

            System.out.println("Client says: " + request);

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String[] parts = request.split(" ", 2);

            if (parts.length < 2) {
                writer.println("Error: book title is required.");
            } else {
                String command = parts[0];
                String title = parts[1];

                command = command.toUpperCase();

                if (command.equals("BORROW")) {
                    if (availableBooks.contains(title)) {
                        availableBooks.remove(title);
                        borrowedBooks.add(title);

                        writer.println(title + " has been borrowed successfully.");
                    } else {
                        writer.println(title + " is not available.");
                    }
                } else if (command.equals("RETURN")) {
                    if (borrowedBooks.contains(title)) {
                        borrowedBooks.remove(title);
                        availableBooks.add(title);

                        writer.println(title + " has been returned successfully.");
                    } else {
                        writer.println("Cannot return " + title + " because it is not borrowed.");
                    }
                } else if (command.equals("SEARCH")) {

                    if (availableBooks.contains(title)) {
                        writer.println(title + " is available.");
                    } else {
                        writer.println(title + " is not available.");
                    }
                } else {
                    writer.println("Unknown command.");
                }

                writer.println("Command received: " + command + ", book:" + title);
            }

            System.out.println("Reply sent to client.");
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
