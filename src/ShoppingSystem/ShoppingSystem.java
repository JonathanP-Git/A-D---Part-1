package ShoppingSystem;

import ShoppingSystem.enums.UserState;
import ShoppingSystem.structure.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class ShoppingSystem {
    HashMap<String,User> users = new HashMap();
    HashMap<String,Account> accounts = new HashMap();

    public void addUser(String line) {
        Scanner scanner = new Scanner(System.in);
        String[] list = line.split(" ");
        String user_id = list[2];
        System.out.println("Please enter password below:");
        String user_password = scanner.nextLine();
        System.out.println("Premium account? y/n:");
        String input = scanner.nextLine();
        boolean premium = input.equals("y");
        System.out.println("Enter user id");
        String customer_id = scanner.nextLine();
        System.out.println("Enter address (The format should be state,city,street,number)");
        String[] address_data = scanner.nextLine().split(",");
        Address customer_address = new Address(address_data[0], address_data[1], address_data[2],
                Integer.parseInt(address_data[3]));
        System.out.println("Enter phone number");
        String phone = scanner.nextLine();
        System.out.println("Enter Email");
        String email = scanner.nextLine();
        System.out.println("Enter account id");
        String account_id = scanner.nextLine();
        System.out.println("Enter billing address");
        String account_billing_address = scanner.nextLine();
        Customer customer = new Customer(customer_id, customer_address, phone, email);
        User user = new User(user_id, user_password, UserState.New, customer);
        ShoppingCart shoppingCart = new ShoppingCart(new Date(), user);
        Account account = new Account(account_id, account_billing_address, customer, shoppingCart);
        shoppingCart.setAccount(account);
        user.setShoppingCart(shoppingCart);
        this.users.put(user.getId(),user);
        this.accounts.put(account.getId(),account);


    }

    public void removeUser() {

    }

    public void login() {

    }

    public void logout() {

    }

    public void newOrder() {

    }

    public void addToOrder() {

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
