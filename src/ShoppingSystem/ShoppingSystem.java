package ShoppingSystem;

import ShoppingSystem.enums.OrderStatus;
import ShoppingSystem.enums.UserState;
import ShoppingSystem.structure.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingSystem {
    HashMap<String, User> users = new HashMap();
    HashMap<String, Account> accounts = new HashMap();
    HashMap<String, Customer> customers = new HashMap();
    HashMap<String, Order> orders = new HashMap();
    int orderNumberCounter = 1;
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
        Account account = new Account(account_id, account_billing_address, customer, shoppingCart);
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
        Order order = new Order(String.valueOf(this.orderNumberCounter ++),new Date(),new Address(address), OrderStatus.New,this.currentUser.getCustomer().getAccount());
        this.orders.put(order.getNumber(),order);
        System.out.println("The order's number is " +order.getNumber());
    }

    public void addProductToOrder(String line) {
        String[] list = line.split(" ");
        String order_id = list[3];
        String login_id = list[4];
        String product_name = list[5];

    }

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
