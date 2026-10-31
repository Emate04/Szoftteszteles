import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ExchangeRateServiceTest {

    @Mock
    private ExchangeRateService exchangeRateService;

    @Test
    @DisplayName("Előre meghatározott árfolyam érték ellenőrzése when().thenReturn() segítségével")
    void testExchangeRateMocking() {
        when(exchangeRateService.getRate("EUR", "HUF")).thenReturn(395.5);
        when(exchangeRateService.getRate("USD", "HUF")).thenReturn(360.0);

        assertEquals(395.5, exchangeRateService.getRate("EUR", "HUF"), 0.001);
        assertEquals(360.0, exchangeRateService.getRate("USD", "HUF"), 0.001);

        verify(exchangeRateService, times(1)).getRate("EUR", "HUF");
        verify(exchangeRateService, times(1)).getRate("USD", "HUF");
    }
}