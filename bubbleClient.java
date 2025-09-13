import java.io.*;
import java.net.*;
import java.util.Scanner;

public class bubbleClient {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public bubbleClient(String host, int port) throws IOException {
        socket = new Socket(host, port);
        System.out.println("Connected to server: " + host + " : " + port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;

            while (true) {
                System.out.print("\nEnter numbers separated by comma OR  command (QUIT) to < Disconnect > : ");
                
                userInput = scanner.nextLine();
                out.println(userInput);
            
                String response = in.readLine();
                if (response == null) break;
                System.out.println("Server: " + response);

                if (userInput.equalsIgnoreCase("QUIT")) {
                        break;
                }
                
            }
        } catch (IOException e) {
            System.out.println("Communication error: " + e.getMessage());
        } finally {
            try {
                socket.close();
                System.out.println("Disconnected.");
            } catch (IOException e) {
                System.out.println("Error closing connection.");
            }
        }
    }

    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter server IP (e.g., localhost): ");
            String host = input.nextLine();

            System.out.print("Enter server port (e.g., 9090): ");
            int port = Integer.parseInt(input.nextLine());

            try {
                bubbleClient client = new bubbleClient(host, port);
                client.start();
            } catch (IOException e) {
                System.out.println("Could not connect to server: " + e.getMessage());
            }
        }
    }
}
