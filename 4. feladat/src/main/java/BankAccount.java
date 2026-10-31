public class BankAccount {
    private double balance;

    public BankAccount() {
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("A befizetett összegnek pozitívnak kell lennie.");
        }
        this.balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("A felvett összegnek pozitívnak kell lennie.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Nincs elegendő fedezet a számlán.");
        }
        this.balance -= amount;
    }
}