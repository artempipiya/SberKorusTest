package sberkorus;

import java.util.Currency;

public abstract class DebitCard extends BankProduct implements WriteOff{
    public DebitCard(String name, Currency currency) {
        super(name, currency);
    }

    @Override
    public void writeOff(Double sum) throws Exception {
        if(balance.compareTo(sum) >= 0) {
            this.balance -= sum;
        } else {
            throwNotEnoughException();
        }
    }
    
    protected void throwNotEnoughException() throws Exception {
        throw new Exception("Не хватает средств для списания суммы");
    }
}