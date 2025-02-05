import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ServerThread implements Runnable{
    private ArrayList<Account> accounts;
    private HashMap<Data, Object> sessionData;
    private Socket socket;
    private Scanner reader;
    private PrintStream writer;

    public ServerThread(Socket server) {
        this.accounts = new ArrayList<Account>(){{
            add(new Account("123456789", "1234", 100));
            add(new Account("987654321", "4321", 10000));
            add(new Account("111111111", "1111", 1000));
        }};
        this.sessionData = new HashMap<Data, Object>();
        this.socket = server;
    }

    @Override
    public void run(){
        try {
            var out = socket.getOutputStream();
            var in = socket.getInputStream();

            reader = new Scanner(in);
            writer = new PrintStream(out);
        }catch (IOException ex){
            ex.printStackTrace();
        }

        ServerLogic();

        try{
            reader.close();
            writer.close();
            socket.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    private void SendMessage(Commands command,
                             String message) {
        Thread thread = Thread.currentThread();
        System.out.println("(S" + thread.getId() + ")[" + command + "] " + message);
        writer.printf("%s:%s\n", command, message);
    }

    private void SendStatus(Commands command,
                            Status status) {
        Thread thread = Thread.currentThread();
        System.out.println("(S" + thread.getId() + ")[" + command + "] " + status);
        writer.printf("%s:%s\n", command, status);
    }

    private void GetMessage(){
        var thread = Thread.currentThread();
        String wholeMessage = reader.nextLine();

        String[] tokens = wholeMessage.split(": ");
        Commands command = Commands.valueOf(tokens[0]);
        String message = tokens[1];

        ProcessMessage(command, message);
    }

    private void ProcessMessage(Commands command, String args) {
        switch (command){
            case WELCOME: {
                SendStatus(Commands.WELCOME, Status.OK);
                break;
            }
            case ASK_ACCOUNT_NUMBER: {
                Status status = accounts
                        .stream()
                        .anyMatch(account -> account.getAccountNumber().equals(args))
                        ? Status.OK
                        : Status.ERROR;

                if (status == Status.OK) {
                    sessionData.put(Data.ACCOUNT_NUMBER, args);
                }
                SendStatus(Commands.ACCOUNT_NUMBER, status);
                break;
            }
            case PIN:{
                String accountNumber = sessionData.get(Data.ACCOUNT_NUMBER).toString();

                Account account = accounts.stream()
                        .filter(acc -> acc
                                .getAccountNumber()
                                .equals(accountNumber))
                        .findFirst()
                        .get();

                Status status = account.checkPin(args)
                        ? Status.OK : Status.ERROR;

                if(status == Status.OK){
                    sessionData.put(Data.ACCOUNT, account);
                }
                SendStatus(Commands.PIN, status);
                break;
            }
            case WITHDRAW: {
                try {
                    Account account = (Account) sessionData.get(Data.ACCOUNT);
                    account.withdraw(Double.parseDouble(args));
                    SendStatus(Commands.WITHDRAW, Status.OK);
                } catch (Exception ex){
                    SendStatus(Commands.WITHDRAW, Status.ERROR);
                    break;
                }
                break;
            }
            case DEPOSIT: {
                try{
                    Account acc = (Account) sessionData.get(Data.ACCOUNT);
                    acc.deposit(Double.parseDouble(args));
                    SendStatus(Commands.DEPOSIT, Status.OK);
                } catch (Exception ex){
                    SendStatus(Commands.DEPOSIT, Status.ERROR);
                    break;
                }
                break;
            }
            case GET_BALANCE: {
                Account acc = (Account) sessionData.get(Data.ACCOUNT);
                SendMessage(Commands.GET_BALANCE, Double.toString(acc.getBalance()));
                break;
            }
            default:
                break;
        }
    }

    private void ServerLogic(){
        // Sending a command to the client to start the communication
        SendMessage(Commands.WELCOME, "Welcome to the ATM");
        GetMessage();

        // Sending a command to the client to ask for the account number
        SendMessage(Commands.ASK_ACCOUNT_NUMBER, "Please enter your account number");
        GetMessage();

        // // Sending a command to the client to ask for the PIN
        SendMessage(Commands.ASK_PIN, "Please enter your PIN");
        GetMessage();

        // // Sending a command to the client to get the balance
        // SendMessage(Commands.GET_BALANCE, "Your balance is $100");
        // GetMessage();

        // // Sending a command to the client to deposit
        // SendMessage(Commands.DEPOSIT, "Please enter the amount to deposit");
        // GetMessage();

        // // Sending a command to the client to withdraw
        // SendMessage(Commands.WITHDRAW, "Please enter the amount to withdraw");
        // GetMessage();

        // // Sending a command to the client to get the balance
        // SendMessage(Commands.ERROR, "Error");
        // GetMessage();
    }
}
