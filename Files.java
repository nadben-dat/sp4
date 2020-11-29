import java.io.*;
import java.util.*;

public class Files {
    private static int lastUsed;
    private static int pizzaSale;

    /**
     * @author Younes Karim
     **/
    // Creation of the archive with placeholder for a correct read later on
    public static void createArchive() {
        try {
            File archive = new File(Main.File);
            File sale = new File(Main.File2);
            if (archive.createNewFile() && sale.createNewFile()) {
                System.out.println("Setting up the Archive");

                FileWriter writer = new FileWriter(Main.File, true);
                BufferedWriter add = new BufferedWriter(writer);
                add.write("0"+
                        "\n⇄ Setting up the archive!\n" + "Ready for input"
                );
                add.newLine();
                add.close();

            } else {
                System.out.println("Archive loaded in!");
            }
        } catch (IOException e) {
            System.out.println("System Error! [4] - "+e+"\n");
        }
    }

    /**
     * @author Younes Karim
     **/
    /**
     * @author Benmessaoud Nadia
     **/
    // Function to write to the archive only if an order is marked as done and needs to be archived
    public static void writeArchive(ArrayList<Pizza> Display, ArrayList<Menu> pizzaMenu, int numb,int newOrder) throws IOException {
        FileWriter writer = new FileWriter(Main.File, true);
        BufferedWriter add = new BufferedWriter(writer);
        add.write(+newOrder +
                "\n⇄ OrderNr" +
                "\nPizza price: " + pizzaMenu.get(Display.get(numb).getPizzaNrOrdered() - 1).getPizzaPrice() +
                " | Was ordered: " + Display.get(numb).isOnlineoroffline() +
                " | Ordered on: " + Display.get(numb).getTime() +
                " | Done on: "+ Count.getdate() +
                " | Pizza Nr: " + Display.get(numb).getPizzaNrOrdered() +
                " | Customer Name: " + Display.get(numb).getCustomerName() +
                " | Pizza Name: " + pizzaMenu.get(Display.get(numb).getPizzaNrOrdered() - 1).getPizzaName()
        );
        add.newLine();
        add.close();
    }

    /**
     * @author Younes Karim
     **/
    /**
     * @author Benmessaoud Nadia

     **/
    // Same function as above but only holds the pizza prices of sold pizzas
    public static void writeSaleArchive(ArrayList<Pizza> Display, ArrayList<Menu> pizzaMenu, int numb) throws IOException {
        FileWriter writer = new FileWriter(Main.File2, true);
        BufferedWriter add = new BufferedWriter(writer);
        add.write(pizzaMenu.get(Display.get(numb).getPizzaNrOrdered() - 1).getPizzaPrice()+"");
        add.newLine();
        add.close();
    }

    /**
     * @author Younes Karim
     **/
    // A small function to read order numbers from the array so that i can sort them according to
    // the last used one but also to keep order
    public static void setUsed() throws FileNotFoundException {
        Scanner counter = new Scanner(new File(Main.File));
        ArrayList<Integer> gatheredOrders = new ArrayList<>();
        int count = 0;
        while (counter.hasNextLine()) {
            count++;
            counter.nextLine();
        }
        try (Scanner scanner = new Scanner(new File(Main.File))) {
            if (scanner.hasNextLine()) {
                for (int i = 0; i < count / 3; i++) { // divide with 3 since i make 3 lines for every order saved.
                    int a = Integer.parseInt(scanner.nextLine());
                    scanner.nextLine();
                    scanner.nextLine();
                    gatheredOrders.add(a);
                }
            } else {
                System.out.println("System Error! [4] - File is empty!\n"+Main.File+" might be corrupted\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("System Error! [4] - FileNotFoundException");
        }
        // Sort statement arraylist
        Collections.sort(gatheredOrders);
        lastUsed = gatheredOrders.get(gatheredOrders.size()-1);
    }

    public static void calculateSale() throws FileNotFoundException {
        Scanner counter = new Scanner(new File(Main.File2));
        ArrayList<Integer> sale = new ArrayList<>();
        int count = 0;
        while (counter.hasNextLine()) {
            count++;
            counter.nextLine();
        }
        try (Scanner scanner = new Scanner(new File(Main.File2))) {
            if (scanner.hasNextLine()) {
                for (int i = 0; i < count; i++) {
                    int b = Integer.parseInt(scanner.nextLine());
                    sale.add(b);
                }
            } else {
                System.out.println("System Error! [4] - File is empty!\n"+Main.File2+" might be corrupted\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("System Error! [4] - FileNotFoundException");
        }
        Integer sum = 0;
        for(Integer a : sale)
            sum += a;
        pizzaSale = sum;
    }

    public static int getPizzaSale() {
        return pizzaSale;
    }
    public static int getLastUsed() {
        return lastUsed;
    }
}