import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    @Test
    @DisplayName("1. Új számla egyenlege 0")
    void testInitialBalanceIsZero() {
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("2. deposit(1000) után az egyenleg 1000")
    void testDepositIncreasesBalance() {
        account.deposit(1000.0);
        assertEquals(1000.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("3. Több befizetés helyesen összeadódik")
    void testMultipleDepositsAddUp() {
        account.deposit(500.0);
        account.deposit(250.0);
        account.deposit(150.0);
        assertEquals(900.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("4. Befizetés után a withdraw() helyesen csökkenti az egyenleget")
    void testWithdrawDecreasesBalance() {
        account.deposit(1000.0);
        account.withdraw(400.0);
        assertEquals(600.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("5. A teljes egyenleg felvehető")
    void testWithdrawFullBalance() {
        account.deposit(1000.0);
        account.withdraw(1000.0);
        assertEquals(0.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("6. Túl nagy összeg felvétele IllegalArgumentException kivételt dob és nem változik az egyenleg")
    void testWithdrawMoreThanBalanceThrowsException() {
        account.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(600.0));
        assertEquals(500.0, account.getBalance(), 0.001); // Egyenleg változatlan
    }

    @Test
    @DisplayName("7. Negatív befizetés kivételt dob és nem változik az egyenleg")
    void testNegativeDepositThrowsException() {
        account.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100.0));
        assertEquals(500.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("8. 0 összegű befizetés kivételt dob és nem változik az egyenleg")
    void testZeroDepositThrowsException() {
        account.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
        assertEquals(500.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("9. Negatív pénzfelvétel kivételt dob és nem változik az egyenleg")
    void testNegativeWithdrawThrowsException() {
        account.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-50.0));
        assertEquals(500.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("10. 0 összegű pénzfelvétel kivételt dob és nem változik az egyenleg")
    void testZeroWithdrawThrowsException() {
        account.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0));
        assertEquals(500.0, account.getBalance(), 0.001);
    }
}