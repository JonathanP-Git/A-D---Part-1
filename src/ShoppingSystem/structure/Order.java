package ShoppingSystem.structure;

import ShoppingSystem.enums.OrderStatus;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    String number;
    Date ordered;
    Date shipped;
    Address shippedTo;
    OrderStatus status;
    float total;
    Account account;
    ArrayList<Payment> payments;
    ArrayList<LineItem> lineItems;

    public Order(String number, Date ordered, Date shipped, Address shippedTo, OrderStatus status, float total,
                 Account account) {
        this.number = number;
        this.ordered = ordered;
        this.shipped = shipped;
        this.shippedTo = shippedTo;
        this.status = status;
        this.total = total;
        this.account = account;
        this.payments = new ArrayList<>();
        this.lineItems = new ArrayList<>();
    }
}
