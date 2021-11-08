package ShoppingSystem.structure;

import java.util.*;

public class ShoppingCart {
    Date created;
    User user;
    ArrayList<LineItem> lineItems;
    Account account;

    public ShoppingCart(Date created, User user) {
        this.created = created;
        this.user = user;
        this.lineItems = new ArrayList<>();
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
