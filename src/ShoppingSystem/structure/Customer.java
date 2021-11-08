package ShoppingSystem.structure;

public class Customer {
    String id;
    Address address;
    String phone;
    String email;
    Account account;
    User user;

    public Customer(String id, Address address, String phone, String email, Account account, User user) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.account = account;
        this.user = user;
    }

    public Customer(String id, Address address, String phone, String email, Account account) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.account = account;
    }

    public void setUser(User user) {
        if (this.user == null) {
            this.user = user;
        } else {
            System.out.println("This customer already has user.");
        }
    }
}
