import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sberkorus.CreditCard;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CreditCardTest {

    String cardName = "МИР-Кредит Платинум";

    @Test
    public void constructorGetLimitTest() throws Exception {
        CreditCard card = new CreditCard(cardName, Currency.getInstance("ILS"));
        card.setLimit(50d);
        Assertions.assertEquals(50d, card.getLimit());
    }

    @Test
    public void writeOffBalanceTest() throws Exception {
        CreditCard card = new CreditCard(cardName, Currency.getInstance("BYN"));
        card.setLimit(50d);
        card.replenish(100d);
        card.writeOff(90d);
        Assertions.assertEquals(10d, card.getBalance());
    }

    @Test
    public void writeOffLimitTest() throws Exception {
        CreditCard card = new CreditCard(cardName, Currency.getInstance("GBP"));
        card.setLimit(50d);
        card.replenish(100d);
        card.writeOff(150d);
        Assertions.assertEquals(-50d, card.getBalance());
    }

    @Test
    public void writeOffExceedTest() {
        CreditCard card = new CreditCard(cardName, Currency.getInstance("RUB"));
        card.setLimit(50d);
        card.replenish(100d);
        Exception exception = assertThrows(Exception.class, () -> {
            card.writeOff(200d);
        });
        String expectedMessage = "Не хватает средств для списания суммы";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Assertions.assertEquals(100d, card.getBalance());
    }
}