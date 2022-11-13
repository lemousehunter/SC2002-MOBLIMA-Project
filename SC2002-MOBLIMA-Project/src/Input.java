import java.util.Objects;
import java.util.Scanner;

/**
 * An Input Object
 * <p>
 * An <code>Input</code> object contains methods to ask for user input
 * </p>
 */
public class Input {
    /**
     * Object of class Scanner
     */
    Scanner sc;

    /**
     * Creates a new object of class Input
     * @param sc Scanner object
     */
    public Input(Scanner sc) {
        this.sc = Objects.requireNonNullElseGet(sc, () -> new Scanner(System.in));
        
    }

    
    /** 
     * Method to display a message and ask for integer input
     * @param msgToPrint Message to be displayed
     * @return ask for user input
     */
    public Integer getInt(String msgToPrint) {
        System.out.print(msgToPrint);
        return this.sc.nextInt();
    }

    
    /** 
     * Method to display a message and ask for double input
     * @param msgToPrint Message to be displayed
     * @return ask for user input
     */
    public double getDouble(String msgToPrint) {
        System.out.print(msgToPrint);
        return this.sc.nextDouble();
    }

    
    /** 
     * Method to display a message and ask for String input
     * @param msgToPrint Message to be displayed
     * @return ask for user input
     */
    public String getLine(String msgToPrint) {
        System.out.print(msgToPrint);
        return this.sc.nextLine();
    }
}
