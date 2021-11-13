package ShoppingSystem;

import ShoppingSystem.enums.OrderStatus;
import ShoppingSystem.enums.UserState;
import ShoppingSystem.structure.*;

import java.util.*;

public class ShoppingSystem {
    HashMap<String, User> users = new HashMap();
    HashMap<String, PremiumAccount> premiumAccounts = new HashMap();
    HashMap<String, Account> accounts = new HashMap();
    HashMap<String, Customer> customers = new HashMap();
    HashMap<String, Order> orders = new HashMap();
    HashMap<String, Product> products = new HashMap();
    HashMap<String, Supplier> suppliers = new HashMap();
    User currentUser = null;
    HashMap<String, Object> allObj = new HashMap<>();

    public void addUser(String line) {
        Scanner scanner = new Scanner(System.in);
        String[] list = line.split(" ");
        String user_id = list[2];
        if (users.containsKey(user_id)) {
            System.out.println(user_id + " is already existed");
            return;
        }
        System.out.println("Please enter password below:");
        String user_password = scanner.nextLine();
        System.out.println("Premium account? y/n:");
        String input = scanner.nextLine();
        boolean premium = input.equals("y");
        System.out.println("Enter user id");
        String customer_id = scanner.nextLine();
        System.out.println("Enter address (The format should be state,city,street,number)");
        String address_data = scanner.nextLine();
        Address customer_address = new Address(address_data);
        System.out.println("Enter phone number");
        String phone = scanner.nextLine();
        System.out.println("Enter Email");
        String email = scanner.nextLine();
        System.out.println("Enter account id");
        String account_id = scanner.nextLine();
        System.out.println("Enter billing address");
        String account_billing_address = scanner.nextLine();
        //
        Customer customer = new Customer(customer_id, customer_address, phone, email);
        User user = new User(user_id, user_password, UserState.New, customer);
        ShoppingCart shoppingCart = new ShoppingCart(new Date(), user);
        Account account;
        if (!premium) {
            account = new Account(account_id, account_billing_address, customer, shoppingCart);
        } else {
            account = new PremiumAccount(account_id, account_billing_address, customer, shoppingCart);
            premiumAccounts.put(account_id, (PremiumAccount) account);
        }
        //
        shoppingCart.setAccount(account);
        user.setShoppingCart(shoppingCart);
        customer.setAccount(account);
        this.users.put(user.getId(), user);
        this.customers.put(customer.getId(), customer);
        this.accounts.put(account.getId(), account);
        System.out.println("The user " + user_id + " has been added!");

    }

    public void removeUser(String line) {
        Scanner scanner = new Scanner(System.in);
        String[] list = line.split(" ");
        String user_id = list[2];
        if (!(users.containsKey(user_id))) {
            System.out.println(user_id + " not existed in the system");
            return;
        }
        this.customers.remove(this.users.get(user_id).getCustomer().getId());
        this.accounts.remove(this.users.get(user_id).getCustomer().getAccount().getId());
        this.users.remove(user_id);
        System.out.println("The user " + user_id + "has been deleted!");


    }

    public void login(String line) {
        String[] list = line.split(" ");
        String user_id = list[2];
        String password = list[3];
        if (currentUser != null) {
            System.out.println("User " + currentUser.getId() + "is already logged in");
            return;
        }
        if (this.users.containsKey(user_id)) {
            if (this.users.get(user_id).getPassword().equals(password)) {
                currentUser = this.users.get(user_id);
            } else {
                System.out.println("The password is incorrect");
            }
        } else {
            System.out.println("User " + user_id + "is not existed");
        }
    }

    public void logout(String line) {
        String[] list = line.split(" ");
        String user_id = list[2];
        if (currentUser.getId().equals(user_id)) {
            currentUser = null;
        } else {
            System.out.println("User " + user_id + "is not existed");
        }
    }

    public void createNewOrder(String line) {
        String[] list = line.split(" ");
        String address = list[3];
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }
        Order order = new Order(new Date(), new Address(address), OrderStatus.New,
                this.currentUser.getCustomer().getAccount());
        this.orders.put(order.getNumber(), order);
        System.out.println("The order's number is " + order.getNumber());
    }

    public void addProductToOrder(String line) {
        String[] list = line.split(" ");
        String order_id = list[3];
        String user_from_id = list[4];
        String product_name = list[5];

        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        // user to buy the product from
        User userToBuy = this.users.get(user_from_id);

        // check if user account is premium account
        PremiumAccount userAcc = premiumAccounts.getOrDefault(userToBuy.getCustomer().getAccount().getId(), null);
        if (userAcc == null) {
            System.out.println("User not has premium account!");
            return;
        }


        ProductOfPremium premiumAccProduct = userAcc.getProduct(product_name);
        if (premiumAccProduct == null) {
            System.out.println("Product not exists in this premium account!");
            return;
        }

        Order order = orders.get(order_id);

        // ask user for quantity
        Scanner Scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("How many " + product_name + " do you want to buy?");
        String userQuantity = Scanner.nextLine();

        boolean addedLineItem = order.addLineItem(premiumAccProduct, Integer.parseInt(userQuantity),
                this.currentUser);
        if (!addedLineItem) {
            return;
        }

        System.out.println("How do you want to pay? (Immediate/Delayed)");
        String userPayment = Scanner.nextLine().toLowerCase();

        System.out.println("Please add information about the payment.");
        String userDetails = Scanner.nextLine().toLowerCase();

        Payment payment = null;
        switch (userPayment) {
            case "immediate" -> {
                System.out.println("Is that your phone number? " + this.currentUser.getCustomer().getPhone() + " y/n");
                String userPhoneConfirmation = Scanner.nextLine().toLowerCase();
                payment = new ImmediatePayment(new Date(),
                        Integer.parseInt(userQuantity) * premiumAccProduct.getPrice(),
                        userDetails, this.currentUser.getCustomer().getAccount(), order,
                        userPhoneConfirmation.equalsIgnoreCase("y"));
            }
            case "delayed" -> {
                System.out.println("Please enter the date of your future payment. dd/mm/yyyy");
                String[] userPaymentDate = Scanner.nextLine().split("/");
                Date paymentDate = new Date(Integer.parseInt(userPaymentDate[2]),
                        Integer.parseInt(userPaymentDate[1]), Integer.parseInt(userPaymentDate[0]));
                payment = new DelayedPayment(new Date(), Integer.parseInt(userQuantity) * premiumAccProduct.getPrice(),
                        userDetails, this.currentUser.getCustomer().getAccount(), order, paymentDate);
            }
            default -> System.out.println("Payment method not recognized! Please try again.");
        }

        if (payment == null) {
            return;
        }
        this.currentUser.getCustomer().getAccount().addPayment(payment);

    }

    public void displayOrder() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        ArrayList<Order> currAccOrders = this.currentUser.getCustomer().getAccount().getOrders();
        Order order = currAccOrders.get(currAccOrders.size() - 1);
        System.out.println("Order number: " + order.getNumber());
        System.out.println("Order date: " + order.getOrdered());
        System.out.println("Shipping date: " + order.getShipped().toString());
        System.out.println("Shipping address: " + order.getShippedTo());
        System.out.println("Order status: " + order.getStatus().toString());
        System.out.println("Total payment " + order.getTotal());
    }


    public void linkProduct(String line) {
        String[] list = line.split(" ");
        String product_name = list[3];
        String price = list[4];
        String quantity = list[5];
        if (currentUser == null || !(premiumAccounts.containsKey(currentUser.getId()))) {
            System.out.println("No user is logged in.");
            return;
        }
        if (!(products.containsKey(product_name))) {
            System.out.println("The product is not available in the system.");
            return;
        }
        Product product = products.get(product_name);
        ((PremiumAccount) currentUser.getCustomer().getAccount()).addProduct(product, Integer.parseInt(price),
                Integer.parseInt(quantity));
        System.out.println("The product has been linked");

    }

    public void addProduct(String line) {
        String[] list = line.split(" ");
        String product_name = list[3];
        String supplier_name = list[4];
        if (this.suppliers.containsKey(supplier_name)) {
            Product product = new Product(product_name, product_name, this.suppliers.get(supplier_name));
            this.products.put(product_name, product);
        } else {
            Supplier supplier = new Supplier(supplier_name, supplier_name);
            this.suppliers.put(supplier.getId(), supplier);
            Product product = new Product(product_name, product_name, supplier);
            this.products.put(product_name, product);
        }
    }

    public void deleteProduct(String line) {
        String[] list = line.split(" ");
        String product_name = list[2];
        if (this.products.containsKey(product_name)) {
            this.products.get(product_name).deleteProductFromSupplier();
            this.products.remove(product_name);
        } else {
            System.out.println("The product is not available in the system.");
        }
    }

    public void showAllObjects() {
        System.out.println("System summery:");
        //
        System.out.println("Users:");

        System.out.println(this.users.toString());
        //
        System.out.println("Premium Accounts:");
        System.out.println(this.premiumAccounts.toString());
        //
        System.out.println("Accounts:");
        System.out.println(this.accounts.toString());
        //
        System.out.println("Customers:");
        System.out.println(this.customers.toString());
        //
        System.out.println("Orders:");
        System.out.println(this.orders.toString());
        //
        System.out.println("Products:");
        System.out.println(this.products.toString());
        //
        System.out.println("Suppliers:");
        System.out.println(this.suppliers.toString());
        //
    }

    public void showObjectId(String line) {
        String id = line.split(" ")[3];
        /*HashMap<String, User> users = new HashMap();
        HashMap<String, PremiumAccount> premiumAccounts = new HashMap();
        HashMap<String, Account> accounts = new HashMap();
        HashMap<String, Customer> customers = new HashMap();
        HashMap<String, Order> orders = new HashMap();
        HashMap<String, Product> products = new HashMap();
        HashMap<String, Supplier> suppliers = new HashMap();

         */
        if(accounts.containsKey(id)){
            Account toPrint = accounts.get(id);
            System.out.println(toPrint);
            System.out.println("Balance: "+toPrint.getBalance());
            System.out.println("Billing Address: "+toPrint.getBilling_address());
            System.out.println("Open Date: "+toPrint.getOpen());
            System.out.println("Close Date: "+toPrint.getClosed());
            System.out.println("Is Closed?: "+toPrint.getIs_closed());
            System.out.println("Customer:" + toPrint.getCustomer());
            System.out.println("Payments: ");
            for(Payment p: toPrint.getPayments()){
                System.out.println(p);
            }
            System.out.println("Orders: ");
            for(Order o: toPrint.getOrders()){
                System.out.println(o);
            }
            System.out.println(toPrint.getShoppingCart());

        }
        if(premiumAccounts.containsKey(id)) {
            PremiumAccount pa = premiumAccounts.get(id);
            for(Product pr: pa.getProducts()){
                System.out.println(pr);
            }
        }

    }

}
