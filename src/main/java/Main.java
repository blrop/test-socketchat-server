import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket acceptedClientSocket = null;

        try {
            serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            System.out.println("Couldn't listen to port");
            System.exit(-1);
        }

        try {
            System.out.print("Waiting for a client...");
            acceptedClientSocket = serverSocket.accept();
            System.out.println("Client connected");
        } catch (IOException e) {
            System.out.println("Can't accept");
            System.exit(-1);
        }

        BufferedReader clientReader = new BufferedReader(new InputStreamReader(acceptedClientSocket.getInputStream()));
        PrintWriter clientWriter = new PrintWriter(acceptedClientSocket.getOutputStream(),true);

        String input;
        System.out.println("Wait for messages");
        while ((input = clientReader.readLine()) != null) {
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            clientWriter.println("S ::: " + input);
            System.out.println(input);
        }
        clientWriter.close();
        clientReader.close();
        acceptedClientSocket.close();
        serverSocket.close();
    }
}
