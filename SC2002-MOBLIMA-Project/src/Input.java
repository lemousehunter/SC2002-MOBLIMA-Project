import java.util.Objects;
import java.util.Scanner;

public class Input {
    Scanner sc;

    public Input(Scanner sc) {
        this.sc = Objects.requireNonNullElseGet(sc, () -> new Scanner(System.in));

    }

    public Integer getInt(String msgToPrint) {
        System.out.println(msgToPrint);
        return this.sc.nextInt();
    }

    public double getDouble(String msgToPrint) {
        System.out.println(msgToPrint);
        return this.sc.nextDouble();
    }

    public String getLine(String msgToPrint) {
        System.out.println(msgToPrint);
        return this.sc.nextLine();
    }
}
