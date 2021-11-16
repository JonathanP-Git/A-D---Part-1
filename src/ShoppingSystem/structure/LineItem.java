package ShoppingSystem.structure;

import java.util.Objects;

public class LineItem {
    String id;
    int quantity;
    int price;
    ShoppingCart shoppingCart;
    Order order;
    Product product;

    public LineItem(int quantity, int price, ShoppingCart shoppingCart, Order order, Product product) {
        this.quantity = quantity;
        this.price = price;
        this.shoppingCart = shoppingCart;
        this.order = order;
        this.product = product;
        this.id = String.valueOf(this.hashCode()); // override this.id

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return quantity == lineItem.quantity && price == lineItem.price && Objects.equals(id, lineItem.id) && Objects.equals(shoppingCart, lineItem.shoppingCart) && Objects.equals(order, lineItem.order) && Objects.equals(product, lineItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity, price, shoppingCart, order, product);
    }

    public void removeFromOrder() {
        this.order.lineItems.remove(this);
    }

    public void removeFromShoppingCart() {
        this.shoppingCart.lineItems.remove(this);
    }
}
