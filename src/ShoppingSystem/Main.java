package ShoppingSystem;

import ShoppingSystem.enums.UserState;
import ShoppingSystem.structure.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;


public class Main {
    public static void main(String[] args) {
        ShoppingSystem shoppingSystem = new ShoppingSystem();
        loadDataToSystem(shoppingSystem);
        processArguments(args);
        try {
            processInput(shoppingSystem,System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadDataToSystem(ShoppingSystem ss) {
        Supplier osem = new Supplier("Osem","Osem");
        Supplier eastWest = new Supplier("EastWest","EastWest");
        Product bamba = new Product("Bamba","Bamba",osem);
        Product ramen = new Product("Ramen","Ramen",eastWest);

        // dani regular account
        Customer daniCustomer = new Customer("Dani",new Address("Tel-Aviv"),"123","abc@gmail.com");
        User dani = new User("Dani","Dani123", UserState.New,daniCustomer);
        ShoppingCart shoppingCart = new ShoppingCart(new Date(), dani);
        Account daniAccount = new Account("Dani","Beer-Sheva",daniCustomer,shoppingCart);

        // Dana premium account
        Customer danaCustomer = new Customer("Dana",new Address("Tel-Aviv"),"123","abc@gmail.com");
        User dana = new User("Dana","Dana123", UserState.New,danaCustomer);
        ShoppingCart shoppingCart2 = new ShoppingCart(new Date(), dana);
        PremiumAccount danaAccount = new PremiumAccount("Dana","Beer-Sheva",daniCustomer,shoppingCart2);
        danaAccount.addProduct(bamba,20,3);

        ss.users.put(dani.getId(),dani);
        ss.users.put(dana.getId(),dana);
        //
        ss.suppliers.put(osem.getId(),osem);
        ss.suppliers.put(eastWest.getId(),eastWest);
        //
        ss.products.put(bamba.getId(),bamba);
        ss.products.put(ramen.getId(),ramen);
        //
        ss.customers.put(daniCustomer.getId(),daniCustomer);
        ss.customers.put(danaCustomer.getId(),danaCustomer);
        //
        ss.accounts.put(daniAccount.getId(),daniAccount);
        ss.accounts.put(danaAccount.getId(),danaAccount);
        //
        ss.premiumAccounts.put(danaAccount.getId(),danaAccount);

        ss.allObj.put(daniAccount.getId(),daniAccount);
        ss.allObj.put(danaAccount.getId(),danaAccount);

    }

    private static void processArguments(String[] args) {
        // whatever
    }

    private static void processInput(ShoppingSystem shoppingSystem, InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        System.out.println("TAFRIT");
        while (!(line = reader.readLine()).equals("exit")) {
            if (line.toLowerCase().startsWith("add user")) {
                shoppingSystem.addUser(line);
            } else if (line.toLowerCase().startsWith("remove user")) {
                shoppingSystem.removeUser(line);
            } else if (line.toLowerCase().startsWith("login user")) {
                shoppingSystem.login(line);
            } else if (line.toLowerCase().startsWith("logout user")) {
                shoppingSystem.logout(line);
            } else if (line.toLowerCase().startsWith("create new order")) {
                shoppingSystem.createNewOrder(line);
            }else if (line.toLowerCase().startsWith("add product to order")) {
                shoppingSystem.addProductToOrder(line);
            } else if (line.toLowerCase().startsWith("display order")) {
                shoppingSystem.displayOrder();
            }
            else if (line.toLowerCase().startsWith("link product")) {
                shoppingSystem.linkProduct(line);
            }
            else if (line.toLowerCase().startsWith("add product")) {
                shoppingSystem.addProduct(line);
            }
            else if (line.toLowerCase().startsWith("delete product")) {
                shoppingSystem.deleteProduct(line);
            }
            else if (line.toLowerCase().startsWith("showallobjects")) {
                shoppingSystem.showAllObjects();
            }else if (line.toLowerCase().startsWith("showobjectid")) {
                shoppingSystem.showObjectId(line);
            }
        }
        in.close();
    }

}







