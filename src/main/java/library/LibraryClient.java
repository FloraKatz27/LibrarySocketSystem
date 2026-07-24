package library;
import java.io.IOException;
import java.net.Socket;
import java.io.PrintWriter;

public class LibraryClient {
    public static void main(String[] args) {
        System.out.println("Starting library client...");

        try {
            Socket socket = new Socket("localhost", 5001);

            System.out.println("Connected to the server!");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("Borrow Harry Potter");

            System.out.println("Request sent to server.");
        } catch (IOException e) {
            System.out.println("Client error:" + e.getMessage());
        }
    }
}
