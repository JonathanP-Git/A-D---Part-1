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

    public User(String id, String password, UserState state,Customer customer) {
        this.id = id;
        this.password = password;
        this.state = state;
        this.shoppingCart = null;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserState getState() {
        return state;
    }

    public void setState(UserState state) {
        this.state = state;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        if (this.shoppingCart == null) {
            this.shoppingCart = shoppingCart;
        } else {
            System.out.println("This user already has shopping cart.");
        }

    }
}
