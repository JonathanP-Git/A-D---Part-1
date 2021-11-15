package ShoppingSystem.structure;

import java.util.Objects;

public class Customer {
    String id;
    Address address;
    String phone;
    String email;
    Account account;
    User user;

//    public Customer(Address address, String phone, String email, Account account, User user) {
//        this.id = String.valueOf(this.hashCode());
//        this.address = address;
//        this.phone = phone;
//        this.email = email;
//        this.account = account;
//        this.user = user;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(address, customer.address) && Objects.equals(phone, customer.phone) && Objects.equals(email, customer.email) && Objects.equals(account, customer.account) && Objects.equals(user, customer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, phone, email);
    }

    public Customer(String id, Address address, String phone, String email) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.id = id;
        this.id = String.valueOf(this.hashCode()); // override this.id
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

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                '}';
    }
}
