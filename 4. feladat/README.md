# 4. Feladat – Mock Objektumok Használata (Mockito)

Ez a projekt a Mockito tesztkeretrendszer és a JUnit 5 használatát mutatja be függőségek mockolásán és viselkedésalapú tesztelésen keresztül.

## Implementált elemek
- **`NotificationService`**: Értesítési szolgáltatás interfésze.
- **`BankService`**: Üzleti logikai osztály, ami Dependency Injection segítségével kapja meg az értesítőt.
- **`ExchangeRateService`**: Árfolyam-lekérdező interfész a `when().thenReturn()` teszteléséhez.

## Használt Mockito funkciók
- `@Mock` / `mock()`
- `verify()`, `times()`, `never()`
- `when().thenReturn()`

## Tesztek futtatása
A tesztek futtatásához navigáljon a `4. feladat` mappába, és futtassa az alábbi parancsot:

```bash
mvn clean test