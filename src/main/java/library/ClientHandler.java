package library;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ClientHandler implements Runnable {

    private Socket clientSocket;
    private ArrayList<String> availableBooks;
    private ArrayList<String> borrowedBooks;
    private Object libraryLock;

    public ClientHandler(Socket clientSocket, ArrayList<String> availableBooks, ArrayList<String> borrowedBooks, Object libraryLock) {
        this.clientSocket = clientSocket;
        this.availableBooks = availableBooks;
        this.borrowedBooks = borrowedBooks;
        this.libraryLock = libraryLock;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            boolean connected = true;

            while (connected) {
                String request = reader.readLine();

                if (request == null) {
                    System.out.println("Client disconnected.");
                    connected = false;
                    continue;
                }

                System.out.println("Client says: " + request);

                if (request.equalsIgnoreCase("EXIT")) {
                    writer.println("Disconnected from server.");
                    connected = false;
                    continue;
                }

                String[] parts = request.split(" ", 2);

                if (parts.length < 2) {
                    writer.println("Error: book title is required.");
                } else {
                    String command = parts[0];
                    String title = parts[1];

                    command = command.toUpperCase();

                    if (command.equals("BORROW")) {
                        synchronized (libraryLock) {
                            if (availableBooks.contains(title)) {
                                availableBooks.remove(title);
                                borrowedBooks.add(title);

                                writer.println(title + " has been borrowed successfully.");
                            } else {
                                writer.println(title + " is not available.");
                            }
                        }
                    } else if (command.equals("RETURN")) {
                        synchronized (libraryLock) {
                            if (borrowedBooks.contains(title)) {
                                borrowedBooks.remove(title);
                                availableBooks.add(title);

                                writer.println(title + " has been returned successfully.");
                            } else {
                                writer.println("Cannot return " + title + " because it is not borrowed.");
                            }
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
                }
            }

            System.out.println("Client conversation has ended");

            clientSocket.close();
        } catch (IOException e) {
            System.out.println("Client handler error: " + e.getMessage());
        }
    }
}
