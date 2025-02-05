public class Account {
    private double balance;
    private String pin;
    private String accountNumber;

    public Account(String pin,
                   String accountNumber,
                   double balance) {
        this.balance = balance;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public boolean checkPin(String pin){
        return this.pin.equals(pin);
    }
}
