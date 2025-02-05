import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args)
        throws UnknownHostException,
               IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while((userInput = scanner.nextLine()) != null) {
            out.println(userInput);
            System.out.println("Server replied: " + in.readLine());
        }

        socket.close();
    }
}
