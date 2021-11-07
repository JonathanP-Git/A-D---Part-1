package ShoppingSystem.structure;

public class Customer {
    String id;
    Address address;
    String phone;
    String email;

    public Customer(String id, Address address, String phone, String email) {
        this.id = id;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
