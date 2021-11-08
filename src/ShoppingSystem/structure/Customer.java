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

    public Customer(String id, Address address, String phone, String email) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account getAccount() {
        return account;
    }

    public User getUser() {
        return user;
    }

    public void setAccount(Account account) {
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
