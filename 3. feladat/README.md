# 3. Feladat – BankAccount Osztály Implementálása és Tesztelése

Ez a projekt egy Java nyelven megvalósított egyszerű bankszámlát (`BankAccount`) modellez, JUnit 5 egységtesztekkel lefedve.

## Funkciók
- **`deposit(double amount)`**: Pénz befizetése a számlára (csak pozitív összeg).
- **`withdraw(double amount)`**: Pénz felvétele a számláról (csak pozitív összeg, fedezet erejéig).
- **`getBalance()`**: Az aktuális egyenleg lekérdezése.
- **Hibakezelés**: Érvénytelen művelet (0, negatív összeg, túlvétel) esetén `IllegalArgumentException` kivételt dob, az egyenleg változatlan marad.

## Tesztelés
A tesztek JUnit 5 keretrendszerrel készültek, lefedve a normál működési eseteket és az összes határértéket/hibadobást.