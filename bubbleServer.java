import java.io.*;
import java.net.*;
import java.util.Arrays;

public class bubbleServer {

    private final ServerSocket serverSocket;
    //private File folder;

    public bubbleServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        // Use current directory as source of files
       // folder = new File(".");
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
                
                String[] strNums = command.split(",");
                int[] arr = new int[strNums.length];

                for (int i = 0; i < strNums.length; i++) {
                    arr[i] = Integer.parseInt(strNums[i]); 
                }
                
                int n = arr.length;
                for (int i = 0; i < n - 1; i++) {
                    for (int j = 0; j < n - 1 - i; j++) {
                        if (arr[j] > arr[j + 1]) {
                            // Swap elements
                            int temp = arr[j];
                            arr[j] = arr[j + 1];
                            arr[j + 1] = temp;
                        }
                    }
                }out.println(Arrays.toString(arr));


                if (command.equalsIgnoreCase("QUIT")) {
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
            bubbleServer server = new bubbleServer(port);
            server.start();
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
