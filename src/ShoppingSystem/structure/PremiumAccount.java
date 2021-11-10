package ShoppingSystem.structure;

import java.util.ArrayList;
import java.util.Date;

public class PremiumAccount extends Account {
    ArrayList<Product> products;

    public PremiumAccount(String id, String billing_address, Customer customer, ShoppingCart shoppingCart) {
        super(id, billing_address, customer, shoppingCart);
        this.products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean addProduct(Product p){
        if(this.products.contains(p)){return false;}
        this.products.add(p);
        return true;
    }


    public Product getProduct(String product_name) {
        for (Product p: this.products
             ) {
            if(p.name.equals(product_name)){
                return p;
            }
        }
        return null;
    }
}

