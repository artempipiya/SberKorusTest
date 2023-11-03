import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sberkorus.DebitCardRuble;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DebitCardRubleTest {

    String cardName = "МИР Платинум";

    @Test
    public void writeOffTest() throws Exception {
        DebitCardRuble card = new DebitCardRuble(cardName);
        card.replenish(100d);
        card.writeOff(50d);
        Assertions.assertEquals(50d, card.getBalance());
    }

    @Test
    public void writeOffMaximumTest() throws Exception {
        DebitCardRuble card = new DebitCardRuble(cardName);
        card.replenish(100d);
        card.writeOff(100d);
        Assertions.assertEquals(0d, card.getBalance());
    }

    @Test
    public void writeOffExceedTest() {
        DebitCardRuble card = new DebitCardRuble(cardName);
        card.replenish(100d);
        Exception exception = assertThrows(Exception.class, () -> {
            card.writeOff(150d);
        });
        String expectedMessage = "Не хватает средств для списания суммы";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
        Assertions.assertEquals(100d, card.getBalance());
    }
}