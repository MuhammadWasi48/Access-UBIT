import java.io.*;
import java.net.*;
import java.util.*;

public class MultiThreadedServer {

    private ServerSocket serverSocket;
    private File folder;

    public MultiThreadedServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("MultiThreaded Server started on port " + port);

        // Use current directory as source of files
        folder = new File(".");
    }

    public void start() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Handle each client in a separate thread
                new Thread(new ClientHandler(clientSocket, folder)).start();

            } catch (IOException e) {
                System.out.println("Connection error: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        int port = 9090;
        try {
            MultiThreadedServer server = new MultiThreadedServer(port);
            server.start();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}

// Thread class for handling a single client
class ClientHandler implements Runnable {
    private Socket socket;
    private File folder;

    public ClientHandler(Socket socket, File folder) {
        this.socket = socket;
        this.folder = folder;
    }

    @Override
    public void run() {
        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String command;
            while ((command = in.readLine()) != null) {
                System.out.println("Received: " + command);

                if (command.equalsIgnoreCase("LIST")) {
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
        } finally {
            try {
                socket.close();
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                System.out.println("Error closing client socket.");
            }
        }
    }
}
