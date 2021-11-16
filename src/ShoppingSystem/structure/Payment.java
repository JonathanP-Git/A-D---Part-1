package ShoppingSystem.structure;

import java.util.Date;
import java.util.Objects;

public abstract class Payment {
    String id;
    Date paid;
    float total;
    String details;
    Account account;
    Order order;

    public Payment(Date paid, float total, String details, Account account, Order order) {
        this.paid = paid;
        this.total = total;
        this.details = details;
        this.account = account;
        this.order = order;
        this.order.addPayment(this);
        this.id = String.valueOf(this.hashCode()); // override this.id

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Float.compare(payment.total, total) == 0 && Objects.equals(id, payment.id) && Objects.equals(paid, payment.paid) && Objects.equals(details, payment.details) && Objects.equals(account, payment.account) && Objects.equals(order, payment.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(paid, total, details, account, order);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id='" + id + '\'' +
                '}';
    }

    public  void removeAccount(){
        this.account = null;
    }

    public  void removeOrder(){
        this.order = null;
    }
}
