package sberkorus;

import java.util.Currency;

public class CreditCard extends DebitCard {

    protected Double percent;
    protected Double debt;
    protected Double limit;

    public CreditCard(String name, Currency currency) {
        super(name, currency);
        this.percent = 0d;
        this.debt = 0d;
        this.limit = 0d;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        assert percent >= 0;
        this.percent = percent;
    }

    public Double getDebt() {
        return debt;
    }

    public Double getLimit() {
        return limit;
    }

    public void setLimit(Double limit) {
        assert limit >= 0;
        this.limit = limit;
    }

    @Override
    public void writeOff(Double sum) throws Exception {
        if (sum.compareTo(balance + limit) <= 0) {
            this.balance -= sum;
        } else {
            throwNotEnoughException();
        }
    }
}