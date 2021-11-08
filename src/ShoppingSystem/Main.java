package ShoppingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import ShoppingSystem.enums.*;
import ShoppingSystem.structure.User;


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
            if (line.toLowerCase().contains("add user")) {
                shoppingSystem.addUser(line);
            } else if (line.toLowerCase().contains("show all objects")) {
                shoppingSystem.showAllObjects();
            } else if (line.toLowerCase().contains("login")) {
                String[] list = line.split(" ");

            } else if (line.toLowerCase().contains("logout")) {
                String[] list = line.split(" ");
            } else if (line.toLowerCase().contains("new order")) {
                System.out.println("hi");
                String[] list = line.split(" ");
            } else if (line.toLowerCase().contains("add user")) {
                System.out.println("hi");
                String[] list = line.split(" ");
            }
        }
        in.close();
    }

}







