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

        Object libraryLock = new Object();

        availableBooks.add("Harry Potter");
        availableBooks.add("Dune");
        availableBooks.add("The Hobbit");
        availableBooks.add("1984");

        //ServerSocket serverSocket;
        try {
            ServerSocket serverSocket = new ServerSocket(5001);

            System.out.println("Server is listening on port 5001.");

            while (true) {

                System.out.println("Waiting for a client...");

                Socket clientSocket = serverSocket.accept();

                System.out.println("A client has connected.");

                ClientHandler handler = new ClientHandler(clientSocket, availableBooks, borrowedBooks, libraryLock);

                Thread clientThread = new Thread(handler);

                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
