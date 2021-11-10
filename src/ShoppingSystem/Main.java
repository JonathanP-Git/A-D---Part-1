package ShoppingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) {
        ShoppingSystem shoppingSystem = new ShoppingSystem();
        processArguments(args);
        try {
            processInput(shoppingSystem,System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            } else if (line.toLowerCase().contains("add user")) {
                System.out.println("hi");
                String[] list = line.split(" ");
            }
        }
        in.close();
    }

}







