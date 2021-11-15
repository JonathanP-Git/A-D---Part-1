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
        this.lineItems = new ArrayList<>();
        this.id = String.valueOf(this.hashCode()); // override this.id

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
}
