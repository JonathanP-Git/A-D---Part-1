package ShoppingSystem.structure;

import java.util.ArrayList;

public class Supplier {
    String id;
    String name;
    ArrayList<Product> products;

    public Supplier(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }
}
