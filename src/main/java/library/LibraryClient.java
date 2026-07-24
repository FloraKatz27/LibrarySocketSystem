package library;
import java.io.IOException;
import java.net.Socket;

public class LibraryClient {
    public static void main(String[] args) {
        System.out.println("Starting library client...");

        try {
            Socket socket = new Socket("localhost", 5001);

            System.out.println("Connected to the server!");
        } catch (IOException e) {
            System.out.println("Client error:" + e.getMessage());
        }
    }
}
