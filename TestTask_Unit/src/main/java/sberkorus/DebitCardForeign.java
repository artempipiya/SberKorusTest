package sberkorus;

import java.util.Currency;

public class DebitCardForeign extends DebitCard {
    public DebitCardForeign(String name, Currency currency) throws Exception {
        super(name, currency);
        if(currency.getCurrencyCode().equals("RUB")){
            throw new Exception("Для создания дебетовой рублёвой карты используйте класс DebitCardRuble");
        }
    }
}