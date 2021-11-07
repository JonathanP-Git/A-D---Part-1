package ShoppingSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        System s = new System();
        processArguments(args);
        try {
            processInput(System.in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processArguments(String[] args) {
        // whatever
    }

    private static void processInput(InputStream in) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String line;
        System.out.println("TAFRIT");
        while (!(line = reader.readLine()).equals("exit")) {
            System.out.println(line.toUpperCase());
            if (line.toLowerCase().contains("add user")) {
                String[] list = line.split(" ");

            }
            else if (line.toLowerCase().contains("remove user")) {
                String[] list = line.split(" ");
            }

            else if (line.toLowerCase().contains("login")) {
                String[] list = line.split(" ");

            }

            else if (line.toLowerCase().contains("logout")) {
                String[] list = line.split(" ");
            }

            else if (line.toLowerCase().contains("new order")) {
                System.out.println("hi");
                String[] list = line.split(" ");
            }

            else if (line.toLowerCase().contains("add user")) {
                System.out.println("hi");
                String[] list = line.split(" ");
            }
        }
        in.close();
    }
}







