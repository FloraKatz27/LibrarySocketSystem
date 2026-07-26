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

            BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            boolean connected = true;

            while (connected) {
                System.out.print("Enter command: ");

                String request = keyboardReader.readLine();

                writer.println(request);

                String response = serverReader.readLine();

                System.out.println("Server replied: " + response);

                if (request.equalsIgnoreCase("EXIT")) {
                    connected = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Client error:" + e.getMessage());
        }
    }
}
