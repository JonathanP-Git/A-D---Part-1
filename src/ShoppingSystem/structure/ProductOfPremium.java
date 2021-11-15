package ShoppingSystem.structure;

import java.util.ArrayList;
import java.util.Objects;

public class ProductOfPremium {
    String id;
    private Product product;
    private int quantity;
    private int price;
    private PremiumAccount premiumAccount;

    public ProductOfPremium(Product product, int quantity, int price, PremiumAccount premiumAccount) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.premiumAccount = premiumAccount;
        this.id = String.valueOf(this.hashCode()); // override this.id

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductOfPremium that = (ProductOfPremium) o;
        return quantity == that.quantity && price == that.price && Objects.equals(id, that.id) && Objects.equals(product, that.product) && Objects.equals(premiumAccount, that.premiumAccount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity, price, premiumAccount);
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

    public void removeConnections() {
        this.premiumAccount = null;
        this.product = null;
    }
}
