package ShoppingSystem.structure;

import java.util.Date;

public class ImmediatePayment extends Payment{
    Boolean phoneConfirmation;

    public ImmediatePayment(String id, Date paid, float total, String details, Account account, Order order,
                            Boolean phoneConfirmation) {
        super(id, paid, total, details, account, order);
        this.phoneConfirmation = phoneConfirmation;
    }
}
