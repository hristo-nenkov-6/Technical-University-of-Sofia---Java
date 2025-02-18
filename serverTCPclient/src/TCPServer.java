import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args)
        throws IOException{
        ServerSocket serverSocket = new ServerSocket(8888);

        while(true){
            Socket clientSocket =
                    serverSocket.accept();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out =
                    new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while((inputLine = in.readLine())!=null){
                System.out.println("Server recieved: " +
                        inputLine);
                out.println(inputLine);
            }
        }
    }
}
