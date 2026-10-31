public class BankService {
    private final NotificationService notificationService;

    public BankService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void processDeposit(BankAccount account, double amount) {
        try {
            account.deposit(amount);
            notificationService.send("Sikeres befizetés: " + amount + " Ft");
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }
}