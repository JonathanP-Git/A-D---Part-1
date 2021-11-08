package ShoppingSystem.structure;

import java.util.ArrayList;
import java.util.Date;

public class Account {
    String id;
    String billing_address;
    boolean is_closed;
    Date open;
    Date closed;
    int balance;
    ArrayList<Payment> payments;
    ArrayList<Order> orders;
    Customer customer;
    ShoppingCart shoppingCart;

    public Account(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance,
                   Customer customer, ShoppingCart shoppingCart) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = is_closed;
        this.open = open;
        this.closed = closed;
        this.balance = balance;
        this.payments = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }
}

