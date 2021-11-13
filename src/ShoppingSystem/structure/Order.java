package ShoppingSystem.structure;

import ShoppingSystem.enums.OrderStatus;

import javax.sound.sampled.Line;
import java.util.ArrayList;
import java.util.Date;

public class Order {
    static int orderNumberCounter = 1;
    String number;
    Date ordered;
    Date shipped;
    Address shippedTo;
    OrderStatus status;
    float total;
    Account account;
    ArrayList<Payment> payments;
    ArrayList<LineItem> lineItems;

    public Order( Date ordered,Address shippedTo, OrderStatus status,
                 Account account) {
        this.number = String.valueOf(orderNumberCounter++);
        this.ordered = ordered;
        this.shipped = null;
        this.shippedTo = shippedTo;
        this.status = status;
        this.total = 0;
        this.account = account;
        this.payments = new ArrayList<>();
        this.lineItems = new ArrayList<>();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getOrdered() {
        return ordered;
    }

    public void setOrdered(Date ordered) {
        this.ordered = ordered;
    }

    public Date getShipped() {
        return shipped;
    }

    public void setShipped(Date shipped) {
        this.shipped = shipped;
    }

    public Address getShippedTo() {
        return shippedTo;
    }

    public void setShippedTo(Address shippedTo) {
        this.shippedTo = shippedTo;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ArrayList<Payment> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Payment> payments) {
        this.payments = payments;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public boolean addLineItem(ProductOfPremium sellerProduct, int userQuantity, User currUser) {
        if(sellerProduct.getQuantity() < userQuantity){
            System.out.println("The seller do not have such number of "+sellerProduct.getProduct().name);
            return false;
        }

        // decrease quantity in seller
        sellerProduct.setQuantity(sellerProduct.getQuantity()-userQuantity);

        this.lineItems.add(new LineItem(userQuantity,sellerProduct.getPrice(),currUser.getShoppingCart(),this,sellerProduct.getProduct()));
        return true;
    }

    @Override
    public String toString() {
        return "Order{" +
                "number='" + number + '\'' +
                '}';
    }
}
