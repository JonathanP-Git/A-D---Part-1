package ShoppingSystem.structure;

import java.util.ArrayList;

public class Product {
    String id;
    String name;
    ArrayList<LineItem> lineItems;
    Supplier supplier;
    PremiumAccount premiumAccount;

    // added fields
    int price;
    int quantity;

    public Product(String id, String name, ArrayList<LineItem> lineItems, Supplier supplier,
                   PremiumAccount premiumAccount,int price,int quantity) {
        this.id = id;
        this.name = name;
        this.lineItems = lineItems;
        this.supplier = supplier;
        this.premiumAccount = premiumAccount;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(String id, String name, ArrayList<LineItem> lineItems, Supplier supplier,int price,int quantity) {
        this.id = id;
        this.name = name;
        this.lineItems = lineItems;
        this.supplier = supplier;
        this.price = price;
        this.quantity = quantity;
    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        if (this.premiumAccount == null){
        this.premiumAccount = premiumAccount;
        }
        else{
            System.out.println("This product already has premium account");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
