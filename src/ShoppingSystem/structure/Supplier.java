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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
