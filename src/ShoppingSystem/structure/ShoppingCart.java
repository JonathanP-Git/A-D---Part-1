package ShoppingSystem.structure;

import java.util.*;

public class ShoppingCart {
    String id;
    Date created;
    User user;
    ArrayList<LineItem> lineItems;
    Account account;

    public ShoppingCart(Date created, User user) {
        this.created = created;
        this.user = user;
        this.user.setShoppingCart(this);
        this.lineItems = new ArrayList<>();
        this.id = String.valueOf(this.hashCode()); // override this.id

    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id='" + id + '\'' +
                ", created=" + created +
                ", user=" + user +
                ", lineItems=" + lineItems +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(id, that.id) && Objects.equals(created, that.created) && Objects.equals(user, that.user) && Objects.equals(lineItems, that.lineItems) && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(created, user);
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Account getAccount() {
        return account;
    }

    public void removeLineItems() {
        for (LineItem li: this.lineItems
             ) {
            li.removeFromOrder();
            li.removeFromProduct();
        }
        this.lineItems.clear();
    }

    public void removeAccount() {
        this.account.removeCustomer();
        this.account.removePayments();
        this.account.removeOrders();

        if(this.account instanceof PremiumAccount){
            PremiumAccount pa = (PremiumAccount) this.account;
            pa.removeProducts();
        }

        this.account = null;
    }
}
