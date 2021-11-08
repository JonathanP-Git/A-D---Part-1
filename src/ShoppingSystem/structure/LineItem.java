package ShoppingSystem.structure;

public class LineItem {
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
    }
}
