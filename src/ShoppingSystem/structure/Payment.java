package ShoppingSystem.structure;

import java.util.Date;

public abstract class Payment {
    String id;
    Date paid;
    float total;
    String details;
    Account account;
    Order order;

    public Payment(String id, Date paid, float total, String details, Account account, Order order) {
        this.id = id;
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.account = account;
        this.order = order;
    }
}
