package sberkorus;


import java.util.Currency;

public abstract class BankProduct implements Replenishment, BalanceRequest {

    protected String name;

    protected Currency currency;
    protected Double balance;

    public BankProduct(String name, Currency currency) {
        this.name = name;
        this.currency = currency;
        this.balance = 0d;
    }

    public String getName() {
        return name;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public void replenish(Double sum) {
        balance += sum;
    }

    @Override
    public Double getBalance() {
        return balance;
    }
}