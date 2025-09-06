import java.io.*;
import java.net.*;
import java.util.*;

public class Server {

    private ServerSocket serverSocket;
    private File folder;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        // Use current directory as source of files
        folder = new File(".");
    }

    public void start() {
        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                handleClient(clientSocket);
            } catch (IOException e) {
                System.out.println("Connection error: " + e.getMessage());
            }
        }
    }

    private void handleClient(Socket socket) {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String command;
            while ((command = in.readLine()) != null) {
                System.out.println("Received: " + command);

                if (command.equalsIgnoreCase("LIST")) {
                    // List all .java files in current folder
                    File[] javaFiles = folder.listFiles((dir, name) -> name.endsWith(".java"));
                    if (javaFiles != null) {
                        List<String> fileNames = new ArrayList<>();
                        for (File f : javaFiles) {
                            fileNames.add(f.getName());
                        }
                        out.println(String.join(",", fileNames));
                    } else {
                        out.println("ERROR: No .java files found");
                    }

                } else if (command.startsWith("GET ")) {
                    String fileName = command.substring(4).trim();
                    File file = new File(folder, fileName);

                    if (file.exists() && file.isFile() && fileName.endsWith(".java")) {
                        // Read file contents
                        StringBuilder content = new StringBuilder();
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                content.append(line).append("\n");
                            }
                        }
                        out.println("CONTENT_START");
                        out.println(content.toString());
                        out.println("CONTENT_END");
                    } else {
                        out.println("ERROR: File not found");
                    }

                } else if (command.equalsIgnoreCase("QUIT")) {
                    out.println("Goodbye!");
                    break;
                } else {
                    out.println("ERROR: Unknown command");
                }
            }
        } catch (IOException e) {
            System.out.println("Client communication error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        int port = 9090;
        try {
            Server server = new Server(port);
            server.start();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
