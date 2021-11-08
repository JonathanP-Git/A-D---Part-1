package ShoppingSystem.structure;

import java.util.*;

public class ShoppingCart {
    Date created;
    User user;
    ArrayList<LineItem> lineItems;
    Account account;

    public ShoppingCart(Date created, User user, Account account) {
        this.created = created;
        this.user = user;
        this.lineItems = new ArrayList<>();
        this.account = account;
        user.setShoppingCart(this);
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
