package ShoppingSystem.structure;

import ShoppingSystem.enums.OrderStatus;

import java.util.Date;

public class Order {
    String number;
    Date ordered;
    Date shipped;
    Address shippedTo;
    OrderStatus status;
    float total;
}
