package ShoppingSystem.structure;

import java.util.Date;

public abstract class Payment {
    static int paymentNumberCounter = 1;
    String id;
    Date paid;
    float total;
    String details;
    Account account;
    Order order;

    public Payment(Date paid, float total, String details, Account account, Order order) {
        this.id = String.valueOf(paymentNumberCounter++);
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.account = account;
        this.order = order;
    }
}
