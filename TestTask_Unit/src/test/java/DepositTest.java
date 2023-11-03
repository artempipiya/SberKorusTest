import org.junit.jupiter.api.Test;
import sberkorus.Deposit;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepositTest {

    String cardName = "Вклад пенсионный";
    
    @Test
    public void closeTest(){
        Deposit deposit = new Deposit(cardName, Currency.getInstance("RUB"));
        deposit.close();
        assertEquals(0d, deposit.getBalance());
    }
}