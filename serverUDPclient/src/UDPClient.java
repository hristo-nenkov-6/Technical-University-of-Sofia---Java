import java.io.*;
import java.net.*;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();

        InetAddress address = InetAddress.getByName("localhost");
        byte[] buffer;

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while ((userInput = scanner.nextLine()) != null) {
            buffer = userInput.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 5000);
            socket.send(packet); // Изпращане на пакет към сървъра

            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet); // Получаване на отговор
            String received = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Server replied: " + received);
        }

        socket.close();
    }


}