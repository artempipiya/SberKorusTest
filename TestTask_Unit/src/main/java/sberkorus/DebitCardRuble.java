package sberkorus;

import java.util.Currency;

public class DebitCardRuble extends DebitCard{
    public DebitCardRuble(String name) {
        super(name, Currency.getInstance("RUB"));
    }
}