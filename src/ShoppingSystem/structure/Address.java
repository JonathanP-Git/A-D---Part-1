package ShoppingSystem.structure;

public class Address {
    String address;
//    String state;
//    String street;
//    String city;
//    int number;

    public Address(String address) {
        this.address = address;
//        this.state = state;
//        this.street = street;
//        this.city = city;
//        this.number = number;
    }

    @Override
    public String toString() {
        return address;
    }
}
