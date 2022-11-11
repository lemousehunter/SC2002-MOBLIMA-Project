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

  
  public void printMainMenuChoiceError() {
    System.out.println("Enter choice betwen 1-9 values only \n");
  }

  public int getScreenMenuChoice() {
    int choice;
    System.out.println(
      "\n========================= Welcome to Staff App =========================\n" +
      "1.  Add Screen                                              \n" +
      "2.  Search Screen                                              \n" +
      "3.  List all Screens                                              \n" +
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

public void printScreenMenuChoiceError() {
  System.out.println("Enter choice betwen 1-4 values only \n");
}

public int getHolidayMenuChoice() {
  int choice;
  System.out.println(
    "\n========================= Welcome to Staff App =========================\n" +
    "1.  Add Holiday                                              \n" +
    "2.  List Holidays                                              \n" +
    "3.  Return to Staff Menu                                              \n" +
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

public void printHolidayMenuChoiceError() {
  System.out.println("Enter choice betwen 1-3 values only \n");

}

public int getTicketPriceMenuChoice() {
  int choice;
  System.out.println(
    "\n========================= Welcome to Staff App =========================\n" +
    "1.  Add Ticket Price                                              \n" +
    "2.  List Ticket Prices                                              \n" +
    "3.  Return to Staff Menu                                              \n" +
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

public int getMovieMenuChoice() {
  int choice;
  System.out.println(
    "\n========================= Welcome to Staff App =========================\n" +
    "1.  Add Movie                                              \n" +
    "2.  Update Movie Details                                              \n" +
    "3.  Update Movie Show Status                                             \n" +
    "4.  List all Movies                                              \n" +
    "5.  Return to Staff Menu                                              \n" +
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

public void printMovieMenuChoiceError() {
  System.out.println("Enter choice betwen 1-6 values only \n");

}

}
