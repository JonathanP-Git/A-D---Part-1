package ShoppingSystem;

import ShoppingSystem.enums.OrderStatus;
import ShoppingSystem.enums.UserState;
import ShoppingSystem.structure.*;

import java.util.*;

public class ShoppingSystem {
    HashMap<String, User> users = new HashMap();
    HashMap<String, PremiumAccount> premiumAcc = new HashMap();
    HashMap<String, Account> accounts = new HashMap();
    HashMap<String, Customer> customers = new HashMap();
    HashMap<String, Order> orders = new HashMap();
    User currentUser = null;

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
        if(!premium) {
            account = new Account(account_id, account_billing_address, customer, shoppingCart);
        }else{
            account = new PremiumAccount(account_id, account_billing_address, customer, shoppingCart);
            premiumAcc.put(account_id, (PremiumAccount) account);
        }
            //
        shoppingCart.setAccount(account);
        user.setShoppingCart(shoppingCart);
        customer.setAccount(account);
        this.users.put(user.getId(), user);
        this.customers.put(customer.getId(), customer);
        this.accounts.put(account.getId(), account);
        System.out.println("The user " + user_id + "has been added!");

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
        }
        else{
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
        Order order = new Order(new Date(),new Address(address), OrderStatus.New,this.currentUser.getCustomer().getAccount());
        this.orders.put(order.getNumber(),order);
        System.out.println("The order's number is " +order.getNumber());
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
        PremiumAccount userAcc = premiumAcc.getOrDefault(userToBuy.getCustomer().getAccount().getId(),null);
        if(userAcc == null){
            System.out.println("User not has premium account!");
            return;
        }


        Product premiumAccProduct = userAcc.getProduct(product_name);
        if (premiumAccProduct == null){
            System.out.println("Product not exists in this premium account!");
            return;
        }

        Order order = orders.get(order_id);

        // ask user for quantity
        Scanner Scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("How many "+product_name+" do you want to buy?");
        String userQuantity = Scanner.nextLine();

        boolean addedLineItem =  order.addLineItem(premiumAccProduct,Integer.parseInt(userQuantity),this.currentUser);
        if(!addedLineItem){return;}

        System.out.println("How do you want to pay? (Immediate/Delayed)");
        String userPayment = Scanner.nextLine().toLowerCase();

        System.out.println("Please add information about the payment.");
        String userDetails = Scanner.nextLine().toLowerCase();

        Payment payment = null;
        switch (userPayment) {
            case "immediate" -> {
                System.out.println("Is that your phone number? " + this.currentUser.getCustomer().getPhone() + " y/n");
                String userPhoneConfirmation = Scanner.nextLine().toLowerCase();
                payment = new ImmediatePayment(new Date(), Integer.parseInt(userQuantity) * premiumAccProduct.getPrice(),
                        userDetails, this.currentUser.getCustomer().getAccount(), order, userPhoneConfirmation.equalsIgnoreCase("y"));
            }
            case "delayed" -> {
                System.out.println("Please enter the date of your future payment. dd/mm/yyyy");
                String[] userPaymentDate = Scanner.nextLine().split("/");
                Date paymentDate = new Date(Integer.parseInt(userPaymentDate[2]), Integer.parseInt(userPaymentDate[1]), Integer.parseInt(userPaymentDate[0]));
                payment = new DelayedPayment(new Date(), Integer.parseInt(userQuantity) * premiumAccProduct.getPrice(),
                        userDetails, this.currentUser.getCustomer().getAccount(), order, paymentDate);
            }
            default -> System.out.println("Payment method not recognized! Please try again.");
        }

        if(payment == null){return;}
        this.currentUser.getCustomer().getAccount().addPayment(payment);

    }
    /*
    public void displayOrder() {
        if (currentUser == null) {
            System.out.println("No user is logged in.");
            return;
        }
        int highest = 0;
        for (int counter = 0; counter < currentUser.getCustomer().getAccount().getOrders().size(); counter++) {
            if (Integer.parseInt(currentUser.getCustomer().getAccount().getOrders().get(counter).getNumber()) > highest) {
                highest = Integer.parseInt(currentUser.getCustomer().getAccount().getOrders().get(counter).getNumber());
            }
        }
        Order order = this.orders.get(highest);
        System.out.println("Order number: " + highest);
        System.out.println("Order date: " + order.get);
        System.out.println("Shipping date: " + order.getShipped().toString());
        System.out.println("Shipping address: " + order.get);
        System.out.println("Order status: " + order.getStatus().toString());
        System.out.println("Total payment " + order.getTotal());
    }
    */

    public void linkProduct() {

    }

    public void addProduct() {

    }

    public void deleteProduct() {

    }

    public void showAllObjects() {
        System.out.println(users);
        System.out.println(accounts);

    }

    public void showObject() {

    }

}