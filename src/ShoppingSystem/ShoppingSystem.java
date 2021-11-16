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
    static public int globalId = 0;

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
//        System.out.println("Enter account id");
//        String account_id = scanner.nextLine();
        System.out.println("Enter billing address");
        String account_billing_address = scanner.nextLine();
        //
        Customer customer = new Customer(customer_id, customer_address, phone, email);
        User user = new User(user_id, user_password, UserState.New, customer);
        ShoppingCart shoppingCart = new ShoppingCart(new Date(), user);
        Account account;
        if (!premium) {
            account = new Account(customer_id, account_billing_address, customer, shoppingCart);
        } else {
            account = new PremiumAccount(customer_id, account_billing_address, customer, shoppingCart);
            premiumAccounts.put(account.getId(), (PremiumAccount) account);
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
        String[] list = line.split(" ");
        String user_id = list[2];
        if (!(users.containsKey(user_id))) {
            System.out.println(user_id + " not existed in the system");
            return;
        }

        // remove from customers hash
        this.customers.remove(this.users.get(user_id).getCustomer().getId());

        String accountID = this.users.get(user_id).getCustomer().getAccount().getId();

        this.accounts.remove(accountID);
        PremiumAccount pAccount = this.premiumAccounts.getOrDefault(accountID,null);
        if(pAccount != null) {
            this.premiumAccounts.remove(this.users.get(user_id).getCustomer().getAccount().getId());
            pAccount.removeConnections();
        }
        this.users.remove(user_id);
        System.out.println("The user " + user_id + " has been deleted!");


    }

    public void login(String line) {
        String[] list = line.split(" ");
        String user_id = list[2];
        String password = list[3];
        if (currentUser != null) {
            System.out.println("User " + currentUser.getId() + " is already logged in");
            return;
        }
        if (this.users.containsKey(user_id)) {
            if (this.users.get(user_id).getPassword().equals(password)) {
                currentUser = this.users.get(user_id);
                System.out.println(currentUser.getId() + " has been logged in");
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
        if (currentUser == null) {
            System.out.println("User " + user_id + " is not logged in");
            return;
        }
        if (currentUser.getId().equals(user_id)) {
            currentUser = null;
            System.out.println("User " + user_id + " successfully logged out");
        } else {
            System.out.println("User " + user_id + " is not logged in");
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
        System.out.println("The order's number/id is " + order.getNumber());
    }

    public void addProductToOrder(String line) {
        String[] list = line.split(" ");
        String order_id = list[4];
        String user_from_id = list[5];
        String product_name = list[6];

        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }

        // user to buy the product from
        User userToBuy = this.users.get(user_from_id);

        if (userToBuy == null) {
            System.out.println("The given user is not existed");
            return;
        }
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

        if (order == null) {
            System.out.println("The order " + order_id + " is not existed");
            return;
        }

        // ask user for quantity
        Scanner Scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("How many " + product_name + " do you want to buy?");
        String userQuantity = Scanner.nextLine();
        order.addTotal(Integer.parseInt(userQuantity)*premiumAccProduct.getPrice());

        boolean addedLineItem = order.addLineItem(premiumAccProduct, Integer.parseInt(userQuantity),
                this.currentUser);
        if (!addedLineItem) {
            return;
        }

        System.out.println("How do you want to pay? (Immediate/Delayed)");
        String userPayment = Scanner.nextLine().toLowerCase();

        System.out.println("Please add details about the payment.");
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
        if (currAccOrders.size() == 0) {
            System.out.println("The user doesn't have orders");
            return;
        }

        Order order = currAccOrders.get(currAccOrders.size() - 1);
        System.out.println("Order number: " + order.getNumber());
        System.out.println("Order date: " + order.getOrdered());
        System.out.println("Shipping date: " + order.getShipped());
        System.out.println("Shipping address: " + order.getShippedTo());
        System.out.println("Order status: " + order.getStatus().toString());
        System.out.println("Total payment " + order.getTotal());
        System.out.println("Payments:");
        for (Payment p : order.getPayments()) {
            System.out.println(p);
        }
        System.out.println("Line items: ");
        for (LineItem p : order.getLineItems()) {
            System.out.println(p);
        }
    }


    public void linkProduct(String line) {
        String[] list = line.split(" ");
        String product_name = list[2];
        String price = list[3];
        String quantity = list[4];
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }
        if(!(currentUser.getCustomer().getAccount() instanceof PremiumAccount)){
            System.out.println("The user doesn't have premium account");
            return;
        }
        if (!(premiumAccounts.containsKey(currentUser.getCustomer().getAccount().getId()))) {
            System.out.println("This user is not a premium account.");
            return;
        }
        if (!(products.containsKey(product_name))) {
            System.out.println("The product is not available in the system.");
            return;
        }
        Product product = products.get(product_name);
        if(((PremiumAccount) currentUser.getCustomer().getAccount()).addProduct(product, Integer.parseInt(price),
                Integer.parseInt(quantity))){
            System.out.println("The product has been linked");
        }
    }

    public void addProduct(String line) {
        String[] list = line.split(" ");
        String product_name = list[2];
        String supplier_name = list[3];
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
            this.products.get(product_name).deleteProductFromPA();
            this.products.get(product_name).deleteLineItems();
            this.products.remove(product_name);

            System.out.println("Product " + product_name + " has been deleted");
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
        System.out.println("Orders and Lineitems:");
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

        if (accounts.containsKey(id)) {
            System.out.println("------ Account ------");
            Account toPrint = accounts.get(id);
            System.out.println(toPrint);
            System.out.println("Balance: " + toPrint.getBalance());
            System.out.println("Billing Address: " + toPrint.getBilling_address());
            System.out.println("Open Date: " + toPrint.getOpen());
            System.out.println("Close Date: " + toPrint.getClosed());
            System.out.println("Is Closed?: " + toPrint.getIs_closed());
            System.out.println("Customer:" + toPrint.getCustomer());
            System.out.println("Payments: ");
            for (Payment p : toPrint.getPayments()) {
                System.out.println(p);
            }
            System.out.println("Orders: ");
            for (Order o : toPrint.getOrders()) {
                System.out.println(o);
            }
            System.out.println(toPrint.getShoppingCart());
            if (premiumAccounts.containsKey(id)) {
                PremiumAccount pa = premiumAccounts.get(id);
                for (Product pr : pa.getProducts()) {
                    System.out.println(pr);
                }
            }
        }
        if (customers.containsKey(id)) {
            System.out.println("------ Customer ------");
            Customer toPrint = customers.get(id);
            System.out.println(toPrint);
            System.out.println("Address: " + toPrint.getAddress());
            System.out.println("Phone: " + toPrint.getPhone());
            System.out.println("Email: " + toPrint.getEmail());
            System.out.println("Customer's User: " + toPrint.getUser());
            System.out.println("Customer's Account: " + toPrint.getAccount());
        }

        if (users.containsKey(id)) {
            System.out.println("------ User ------");
            User toPrint = users.get(id);
            System.out.println(toPrint);
            System.out.println("Login id: " + toPrint.getId());
            System.out.println("Password: " + "********");
            System.out.println("State: " + toPrint.getState().toString());
            System.out.println("Users's Customer: " + toPrint.getCustomer());
            System.out.println("Users's shopping cart: " + toPrint.getShoppingCart());
        }

        if (products.containsKey(id)) {
            System.out.println("------ Product------");
            Product toPrint = products.get(id);
            System.out.println(toPrint);
            System.out.println("ID: " + toPrint.getId());
            System.out.println("Name: " + toPrint.getName());
            if (toPrint.getPremiumAccount() != null) {
                System.out.println("Premium account: " + toPrint.getPremiumAccount().getId());
            }
            System.out.println("Product's Supplier: " + toPrint.getSupplier());
            System.out.println("Product's Line items: ");
            for (LineItem li : toPrint.getLineItems()) {
                System.out.println(li);
            }
        }

        if (suppliers.containsKey(id)) {
            System.out.println("------ Supplier------");
            Supplier toPrint = suppliers.get(id);
            System.out.println(toPrint);
            System.out.println("ID: " + toPrint.getId());
            System.out.println("Name: " + toPrint.getName());
            for (Product p : toPrint.getProducts()) {
                System.out.println(p);
            }
        }

        if (orders.containsKey(id)) {
            System.out.println("------ Orders------");
            Order toPrint = orders.get(id);
            System.out.println("ID: " + toPrint.getId());
            System.out.println("Number: " + toPrint.getNumber());
            System.out.println("Ordered date: " + toPrint.getOrdered());
            System.out.println("Shipped date: " + toPrint.getShipped());
            System.out.println("Status: " + toPrint.getStatus().toString());
            System.out.println("Total: " + toPrint.getTotal());
            System.out.println("Account: " + toPrint.getAccount().toString());
            System.out.println("Payments:");
            for (Payment p : toPrint.getPayments()) {
                System.out.println(p);
            }
            System.out.println("Line items: ");
            for (LineItem p : toPrint.getLineItems()) {
                System.out.println(p);
            }
        }
    }
}
