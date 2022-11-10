import java.util.*;

public class StaffBoundary {

  private Scanner s;
  private Scanner q;
  private int choice;

  public StaffBoundary() {
    s = new Scanner(System.in);
    q = new Scanner(System.in);
    choice = 0;
  }

  public int getMainMenuChoice() {
    System.out.println(
      "\n========================= Welcome to Staff App =========================\n" +
      "1.  Manage Cineplex                                              \n" +
      "2.  Manage Screen                                              \n" +
      "3.  Manage Movies                                            \n" +
      "4.  Manage Shows                                 \n" +
      "5.  Manage Ticket Prices                                \n" +
      "6.  Manage Holidays                                \n" +
      "7.  List Top 5 Movies by Sales                             \n" +
      "8.  List Top 5 Movies by Ratings                               \n" +
      "9.  Exit application                                   \n" +
      "========================================================================"
    );
    System.out.print("\nEnter choice: ");
    while (!s.hasNextInt()) {
      System.out.println("Please enter an integer value. \n");
      s.next();
    }

    choice = s.nextInt();
    return choice;
  }

  public int getCineplexMenuChoice() {
    int choice;
    System.out.println(
      "\n========================= Welcome to Staff App =========================\n" +
      "1.  Add Cineplex                                              \n" +
      "2.  Search Cineplex                                              \n" +
      "3.  List all Cineplexes                                              \n" +
      "4.  Return to Staff Menu                                              \n" +
      "========================================================================"
    );
    System.out.print("\nEnter choice: ");
    while (!s.hasNextInt()) {
      System.out.println("Please enter an integer value. \n");
      s.next();
    }

    choice = s.nextInt();
    return choice;
  }

  
  public void printMainMenuChoiceError() {
    System.out.println("Enter choice betwen 1-9 values only \n");
  }


  public void printCineplexMenuChoiceError() {
    System.out.println("Enter choice betwen 1-2 values only \n");
  }

}
