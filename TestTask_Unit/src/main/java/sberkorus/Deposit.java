package sberkorus;

import java.util.Currency;

public class Deposit extends BankProduct implements CloseDeposit{
    public Deposit(String name, Currency currency) {
        super(name, currency);
    }

    @Override
    public void close() {
        this.balance = 0d;
    }
}