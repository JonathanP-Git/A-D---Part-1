package ShoppingSystem.structure;

import java.util.ArrayList;

public class Product {
    String id;
    String name;
    ArrayList<LineItem> lineItems;
    Supplier supplier;
    PremiumAccount premiumAccount;



    public Product(String id, String name, Supplier supplier,
                   PremiumAccount premiumAccount) {
        this.id = id;
        this.name = name;
        this.lineItems = new ArrayList<>();
        this.supplier = supplier;
        this.premiumAccount = premiumAccount;

    }

    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.lineItems = new ArrayList<>();
        this.supplier = supplier;
        this.premiumAccount = null;

    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        if (this.premiumAccount == null){
        this.premiumAccount = premiumAccount;
        }
        else{
            System.out.println("This product already has premium account");
        }
    }
}
