package ShoppingSystem.structure;

import java.util.ArrayList;

public class Product {
    String id;
    String name;
    ArrayList<LineItem> lineItems;
    Supplier supplier;
    PremiumAccount premiumAccount;



//    public Product(String id, String name, Supplier supplier,
//                   PremiumAccount premiumAccount) {
//        this.id = id;
//        this.name = name;
//        this.lineItems = new ArrayList<>();
//        this.supplier = supplier;
//        this.premiumAccount = premiumAccount;
//
//    }

    public Product(String id, String name, Supplier supplier) {
        this.id = id;
        this.name = name;
        this.lineItems = new ArrayList<>();
        this.supplier = supplier;
        this.supplier.products.add(this);
        this.premiumAccount = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public PremiumAccount getPremiumAccount() {
        return premiumAccount;
    }

    public void setPremiumAccount(PremiumAccount premiumAccount) {
        if (this.premiumAccount == null){
        this.premiumAccount = premiumAccount;
        }
        else if (premiumAccount == null){
            this.premiumAccount = null;
        }
        else{
            System.out.println("This product already has premium account");
        }
    }

    public void deleteProductFromSupplier(){
       this.supplier.removeProduct(this);
    }
    public void deleteProductFromPA(){
       this.premiumAccount.removeProduct(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                '}';
    }

    public void deleteLineItems() {
        for(LineItem li: this.lineItems){
            li.removeFromOrder();
            li.removeFromShoppingCart();
        }
        this.lineItems.clear();
    }
}
