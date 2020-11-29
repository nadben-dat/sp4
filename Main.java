import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
//  What is working so far
/*
1. you can see the menu with all the pizzas (Marios option).
2. you can order a specific pizza, with name, and location (store or phone).
3. you can see all your active orders (Marios option).
4. you can finish any given order --> saving it to a file for later (Alfonso option).
5. you can calculate the sale revenue from all finished and archived orders (Marios option).

ToDo list
[Sort orders after time of pickup] - This sounds optional so im not adding since i already have all the orders sorted by all lifetime orders
*/
//System Errors
/*
//System Error! [1] --> No orders - makePizza()
//System Error! [2] --> Pizza creation error - pizzaOrders()
//System Error! [3] --> Empty array - pizzaFinisher()
//System Error! [4] --> IOException error, empty file or file did not load error
//System Error! [5] --> Order number is out of bounds
*/

/**
 * @Benmessaoud Nadia
 */

public class Main {
    // Location of the archive text document
    public static String File = "./Archive.txt";
    public static String File2 = "./PizzaSale.txt";

    /**
     * @author Younes Karim
     **/
    public static void main(String[] args) throws IOException {
        //Pizza factory
        ArrayList<Menu> pizzaMenu = new ArrayList<>();
        ArrayList<Pizza> customer = new ArrayList<>();
        Pizzas.addPizzas(pizzaMenu);
        Files.createArchive();
        Scanner cmd = new Scanner(System.in);
        Menu.start(cmd, pizzaMenu, customer);
    }
}
