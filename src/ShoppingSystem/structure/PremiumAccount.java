package ShoppingSystem.structure;

import java.util.ArrayList;
import java.util.Date;

class PremiumAccount extends Account {
    ArrayList<Product> products;

    public PremiumAccount(String id, String billing_address, boolean is_closed, Date open, Date closed, int balance,
                          Customer customer, ShoppingCart shoppingCart) {
        super(id, billing_address, is_closed, open, closed, balance, customer, shoppingCart);
        this.products = new ArrayList<>();
    }
}

