package ShoppingSystem.structure;

import ShoppingSystem.enums.UserState;

public class User {
    String id;
    String password;
    UserState state;
    ShoppingCart shoppingCart;
    Customer customer;

    public User(String id, String password, UserState state, ShoppingCart shoppingCart, Customer customer) {
        this.id = id;
        this.password = password;
        this.state = state;
        this.shoppingCart = shoppingCart;
        this.customer = customer;
    }

    public User(String id, String password, UserState state, Customer customer) {
        this.id = id;
        this.password = password;
        this.state = state;
        this.customer = customer;
        this.customer.setUser(this);
        this.shoppingCart = null;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if (this.shoppingCart == null) {
            this.shoppingCart = shoppingCart;
        } else {
            System.out.println("This user already has shopping cart.");
        }

    }
}
