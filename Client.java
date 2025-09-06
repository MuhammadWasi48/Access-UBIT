import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
        System.out.println("Connected to server: " + host + " : " + port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            String userInput;

            while (true) {
                System.out.print("\nEnter command (LIST, GET <filename>, QUIT): ");
                userInput = scanner.nextLine();
                out.println(userInput);

                if (userInput.startsWith("GET ")) {
                    String fileName = userInput.substring(4).trim();

                    StringBuilder fileContent = new StringBuilder();
                    String line;

                    // Wait for content markers
                    while ((line = in.readLine()) != null) {
                        if (line.equals("CONTENT_START")) {
                            continue;
                        } else if (line.equals("CONTENT_END")) {
                            break;
                        } else if (line.startsWith("ERROR")) {
                            System.out.println("Server: " + line);
                            break;
                        } else {
                            fileContent.append(line).append("\n");
                        }
                    }

                    if (fileContent.length() > 0) {
                        // Write content to a new .txt file
                        String outputFileName = fileName.replace(".java", "_copy.txt");
                        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
                            writer.write(fileContent.toString());
                        }
                        System.out.println("File saved as: " + outputFileName);
                    }

                } else {
                    String response = in.readLine();
                    if (response == null) break;
                    System.out.println("Server: " + response);

                    if (userInput.equalsIgnoreCase("QUIT")) {
                        break;
                    }
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
                Client client = new Client(host, port);
                client.start();
            } catch (IOException e) {
                System.out.println("Could not connect to server: " + e.getMessage());
            }
        }
    }
}
