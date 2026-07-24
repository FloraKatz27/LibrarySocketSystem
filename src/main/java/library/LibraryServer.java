package library;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class LibraryServer {
    public static void main(String[] args) {
        System.out.println("Starting library server...");

        //ServerSocket serverSocket;
        try {
            ServerSocket serverSocket = new ServerSocket(5001);

            System.out.println("Server is listening on port 5001.");

            System.out.println("Waiting for a client...");

            Socket clientSocket = serverSocket.accept();

            System.out.println("A client has connected.");
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
