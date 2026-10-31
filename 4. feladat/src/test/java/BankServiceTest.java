import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BankServiceTest {

    @Mock
    private NotificationService notificationService;

    private BankService bankService;
    private BankAccount account;

    @BeforeEach
    void setUp() {
        bankService = new BankService(notificationService);
        account = new BankAccount();
    }

    @Test
    @DisplayName("1. A befizetés megfelelően növeli a számla egyenlegét")
    void testDepositIncreasesBalance() {
        bankService.processDeposit(account, 1000.0);
        assertEquals(1000.0, account.getBalance(), 0.001);
    }

    @Test
    @DisplayName("2. Sikeres befizetés után meghívódik a NotificationService.send() metódusa")
    void testNotificationSentOnSuccessfulDeposit() {
        bankService.processDeposit(account, 500.0);

        verify(notificationService, times(1)).send("Sikeres befizetés: 500.0 Ft");
    }

    @Test
    @DisplayName("3. Sikertelen befizetés esetén nem történik értesítés")
    void testNoNotificationOnFailedDeposit() {
        assertThrows(IllegalArgumentException.class, () -> bankService.processDeposit(account, -100.0));

        verify(notificationService, never()).send(anyString());
        assertEquals(0.0, account.getBalance(), 0.001);
    }
}