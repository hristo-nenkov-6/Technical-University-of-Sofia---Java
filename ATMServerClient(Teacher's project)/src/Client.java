import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Client {
    public static Socket socket;
    public static Scanner reader;
    public static PrintStream writer;
    public static Scanner scanner;

    public static void main(String[] args)
        throws java.io.IOException {
        try{
            socket = new Socket("127.0.0.1", 8080);
            OutputStream out = socket.getOutputStream();
            InputStream in = socket.getInputStream();

            reader = new Scanner(in);
            writer = new PrintStream(out);

            scanner = new Scanner(System.in);

            RunLogic();

            reader.close();
            writer.close();
            socket.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void PerformAction(String wholeMessage){
        String[] tokens = wholeMessage.split(":");
        Commands cmd = Commands.valueOf(tokens[0]);
        String args = tokens[1];
        Thread thread = Thread.currentThread();

        System.out.println("(R" + thread.getId() + ")[" + cmd  + "]: " + args);

        switch (cmd)
        {
            case WELCOME:
                // Sending a command to the server
                SendMessage(Commands.WELCOME, "Welcome from the client");
                break;
            case ASK_ACCOUNT_NUMBER:
                var accountNumber = scanner.nextLine();
                // Sending a command to the server
                SendMessage(Commands.ACCOUNT_NUMBER, accountNumber);
                break;
            case ACCOUNT_NUMBER:
                // Sending a command to the server
                System.out.println("Account number: " + args);
                break;
            case PIN:
                // Sending a command to the server
                System.out.println("PIN: " + args);
                break;
            case ASK_PIN:
                // Sending a command to the server
                var pin = scanner.nextLine();
                SendMessage(Commands.PIN, pin);
                break;
            case WITHDRAW:
                // Sending a command to the server
                var amount = scanner.nextLine();
                SendMessage(Commands.WITHDRAW, amount);
                break;
            case DEPOSIT:
                // Sending a command to the server
                amount = scanner.nextLine();
                SendMessage(Commands.DEPOSIT, amount);
                break;
            case GET_BALANCE:
                // Sending a command to the server
                SendStatus(Commands.GET_BALANCE, Status.OK);
                break;
            case ERROR:
                // Sending a command to the server
                SendStatus(Commands.GET_BALANCE, Status.ERROR);
                break;
            default:
                // Printing an error message if the command is not recognized
                SendMessage(Commands.ERROR, "Command not recognized");
                break;
        }
    }

    public static void SendMessage(Commands command, String message)
    {
        var thread = Thread.currentThread();
        System.out.println("(S" + thread.getId() + ")[" + command + "] " + message);
        writer.printf("%s:%s\n", command, message);

        String wholeMessage = reader.nextLine();
        System.out.println("(R" + thread.getId() + ")[" + command + "] " + wholeMessage.split(":")[1]);
    }

    public static void SendStatus(Commands command, Status status)
    {
        var thread = Thread.currentThread();
        System.out.println("(S" + thread.getId() + ")[" + command + "] " + status);
        writer.printf("%s:%s\n", command, status);
    }

    public static void RunLogic()
    {
        String wholeMessage = null;

        do {
            wholeMessage = reader.nextLine();

            PerformAction(wholeMessage);
        } while (wholeMessage != null && !wholeMessage.isEmpty() && !wholeMessage.isBlank());
    }
}
