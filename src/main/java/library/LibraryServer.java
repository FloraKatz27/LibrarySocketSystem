package library;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;


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

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String request = reader.readLine();

            System.out.println("Client says: " + request);
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
