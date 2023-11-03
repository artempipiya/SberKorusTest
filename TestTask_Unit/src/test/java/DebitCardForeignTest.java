import org.junit.jupiter.api.Test;
import sberkorus.DebitCardForeign;

import java.util.Currency;

import static org.junit.jupiter.api.Assertions.*;

public class DebitCardForeignTest {
    
    String cardName = "Maestro";

    @Test
    public void constructorRubTest() {
        Exception exception = assertThrows(Exception.class, () -> {
            new DebitCardForeign(cardName, Currency.getInstance("RUB"));
        });
        String expectedMessage = "Для создания дебетовой рублёвой карты используйте класс DebitCardRuble";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void constructorEurTest() throws Exception {
        DebitCardForeign card = new DebitCardForeign(cardName, Currency.getInstance("EUR"));
        assertEquals("EUR", card.getCurrency().getCurrencyCode());
    }
}