package library;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.PrintWriter;

public class LibraryClient {
    public static void main(String[] args) {
        System.out.println("Starting library client...");

        try {
            Socket socket = new Socket("localhost", 5001);

            System.out.println("Connected to the server!");

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            writer.println("BORROW Harry Potter");

            System.out.println("Request sent to server.");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String response = reader.readLine();

            System.out.println("Server replied: " + response);
        } catch (IOException e) {
            System.out.println("Client error:" + e.getMessage());
        }
    }
}
