package ShoppingSystem.structure;

import java.util.Date;

public class DelayedPayment extends Payment{
    Date paymentDate;

    public DelayedPayment(Date paid, float total, String details, Account account, Order order,
                          Date paymentDate) {
        super(paid, total, details, account, order);
        this.paymentDate = paymentDate;
    }
}
