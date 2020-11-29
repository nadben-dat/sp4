import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Count {

    /**
     * @author Younes Karim
     **/
    // count the sale revenue from file
    public static void countTheMoney() throws FileNotFoundException {
        Files.calculateSale();
        System.out.println("The Total sale revenue for all pizzas sold is: \n"+Files.getPizzaSale()+"kr.\n");
    }



    /**
     * @author Younes Karim
     **/
    // get the current date
    public static String getdate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yy @ HH:mm:ss");
        return date.format(format);
    }
}
