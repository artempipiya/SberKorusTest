import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sberkorus.Deposit;

import java.util.Currency;

public class BankProductTest {
    String name = "Пенсионный вклад";
    
    @Test
    public void getNameTest(){
        Deposit deposit = new Deposit(name, Currency.getInstance("RUB"));
        Assertions.assertEquals(name, deposit.getName());
    }

    @Test
    public void getCurrencyRubTest(){
        Deposit deposit = new Deposit(name, Currency.getInstance("RUB"));
        Assertions.assertEquals("RUB", deposit.getCurrency().getCurrencyCode());
    }

    @Test
    public void getCurrencyUsdTest(){
        Deposit deposit = new Deposit(name, Currency.getInstance("USD"));
        Assertions.assertEquals("USD", deposit.getCurrency().getCurrencyCode());
    }

    @Test
    public void balanceRequestZeroTest(){
        Deposit deposit = new Deposit(name, Currency.getInstance("RUB"));
        Assertions.assertEquals(0d, deposit.getBalance());
    }

    @Test
    public void balanceRequestSmallTest(){
        Double sum = 0.01d;
        Deposit deposit = new Deposit(name, Currency.getInstance("RUB"));
        deposit.replenish(sum);
        Assertions.assertEquals(sum, deposit.getBalance());
    }

    @Test
    public void balanceRequestBigTest(){
        Double sum = 10000000.01d;
        Deposit deposit = new Deposit(name, Currency.getInstance("RUB"));
        deposit.replenish(sum);
        Assertions.assertEquals(sum, deposit.getBalance());
    }
}