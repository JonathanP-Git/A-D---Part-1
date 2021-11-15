package ShoppingSystem.structure;

import java.util.Objects;

public class Address {
    String address;
    String id;
//    String state;
//    String street;
//    String city;
//    int number;

    public Address(String address) {
        this.address = address;
        this.id = String.valueOf(this.hashCode()); // override this.id
//        this.state = state;
//        this.street = street;
//        this.city = city;
//        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address1 = (Address) o;
        return Objects.equals(address, address1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        return address;
    }
}
