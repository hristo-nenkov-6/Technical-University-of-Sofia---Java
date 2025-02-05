import javax.xml.transform.Source;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CarDealerServer {
    private static final int port = 8080;
    private static final List<Car> carsForSale =
            new ArrayList<Car>();

    public static void main(String[] args){
        carsForSale.add(new Car("Toyota", "Corolla", 20000));
        carsForSale.add(new Car("BMW", "X5", 50000));
        carsForSale.add(new Car("Audi", "A4", 30000));

        try(ServerSocket serverSocket = new ServerSocket(port)){
            System.out.println("Server is started on port " + port);

            ExecutorService clientPool = Executors.newFixedThreadPool(3);

            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " +
                        clientSocket.getInetAddress());
                clientPool.submit(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler implements Runnable{
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket){
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try{
                InputStream in = new BufferedInputStream(clientSocket.getInputStream());
                OutputStream out = new BufferedOutputStream(clientSocket.getOutputStream());

                while (true) {
                    Str
                }
            }
        }
    }
}
