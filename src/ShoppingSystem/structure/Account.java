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

    public Account(String id, String billing_address,
                   Customer customer, ShoppingCart shoppingCart) {
        this.id = id;
        this.billing_address = billing_address;
        this.is_closed = false;
        this.open = new Date();
        this.balance = 0;
        this.payments = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customer = customer;
        this.shoppingCart = shoppingCart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBilling_address() {
        return billing_address;
    }

    public void setBilling_address(String billing_address) {
        this.billing_address = billing_address;
    }

    public boolean isIs_closed() {
        return is_closed;
    }

    public void setIs_closed(boolean is_closed) {
        this.is_closed = is_closed;
    }

    public Date getOpen() {
        return open;
    }

    public void setOpen(Date open) {
        this.open = open;
    }

    public Date getClosed() {
        return closed;
    }

    public void setClosed(Date closed) {
        this.closed = closed;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addPayment(Payment p){this.payments.add(p);}

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                '}';
    }
}

