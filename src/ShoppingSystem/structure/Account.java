package ShoppingSystem.structure;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

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

    public Account(String id, String billing_address,Customer customer, ShoppingCart shoppingCart) {

        this.id = String.valueOf(this.hashCode()); // override this.id
        this.billing_address = billing_address;
        this.is_closed = false;
        this.open = new Date();
        this.balance = 100;
        this.payments = new ArrayList<>();
        this.orders = new ArrayList<>();
        this.customer = customer;
        this.customer.setAccount(this);
        this.shoppingCart = shoppingCart;
        this.shoppingCart.setAccount(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return is_closed == account.is_closed && balance == account.balance && Objects.equals(id, account.id) && Objects.equals(billing_address, account.billing_address) && Objects.equals(open, account.open) && Objects.equals(closed, account.closed) && Objects.equals(payments, account.payments) && Objects.equals(orders, account.orders) && Objects.equals(customer, account.customer) && Objects.equals(shoppingCart, account.shoppingCart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, billing_address,customer, shoppingCart);
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

    public boolean getIs_closed() {
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

    public void putOrder(Order order){
        this.orders.add(order);
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

    public void removeCustomer() {
        this.customer.removeUser();
        this.customer.removeAccount();
        this.customer = null;
    }

    public void removePayments() {
        for (Payment p:this.payments
             ) {
            p.removeAccount();
            p.removeOrder();
        }
        this.payments.clear();
    }

    public void removeOrders() {
        for (Order o: this.orders
             ) {
            o.removePayment();
            o.removeLineItems();
        }
        this.orders.clear();
    }
}

