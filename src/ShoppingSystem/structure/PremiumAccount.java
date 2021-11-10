package ShoppingSystem.structure;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PremiumAccount extends Account {
    ArrayList<ProductOfPremium> products_quantity_price;

    public ArrayList<ProductOfPremium> getProducts_quantity_price() {
        return products_quantity_price;
    }

    public void setProducts_quantity_price(ArrayList<ProductOfPremium> products_quantity_price) {
        this.products_quantity_price = products_quantity_price;
    }

    public PremiumAccount(String id, String billing_address, Customer customer, ShoppingCart shoppingCart) {
        super(id, billing_address, customer, shoppingCart);
        this.products_quantity_price = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        for (ProductOfPremium productOfPremium : this.products_quantity_price) {
            products.add(productOfPremium.getProduct());
        }
        return products;
    }

    public boolean addProduct(Product p, int quantity, int price){
        ArrayList<Product> products = new ArrayList<>();
        for (ProductOfPremium productOfPremium : this.products_quantity_price) {
            products.add(productOfPremium.getProduct());
        }
        if(products.contains(p)){return false;}
        this.products_quantity_price.add(new ProductOfPremium(p,quantity,price,this));
        return true;
    }


    public ProductOfPremium getProduct(String product_name) {
        for (ProductOfPremium productOfPremium : this.products_quantity_price)
        {
            if(productOfPremium.getProduct().name.equals(product_name)){
                return productOfPremium;
            }
        }
        return null;
    }
}

