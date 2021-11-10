package ShoppingSystem.structure;

import java.util.ArrayList;

public class ProductOfPremium {
    private Product product;
    private int quantity;
    private int price;
    private PremiumAccount premiumAccount;

    public ProductOfPremium(Product product, int quantity, int price, PremiumAccount premiumAccount) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.premiumAccount = premiumAccount;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
