package ShoppingSystem.structure;

import java.util.ArrayList;
import java.util.Date;

class PremiumAccount extends Account {
    ArrayList<Product> products;

    public PremiumAccount(String id, String billing_address, Customer customer, ShoppingCart shoppingCart,
                          ArrayList<Product> products) {
        super(id, billing_address, customer, shoppingCart);
        this.products = products;
    }
}

