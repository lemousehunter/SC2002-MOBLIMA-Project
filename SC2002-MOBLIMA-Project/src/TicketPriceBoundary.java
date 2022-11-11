import java.util.ArrayList;
import java.util.Scanner;

public class TicketPriceBoundary {

  private Scanner s;
  private Scanner q;

  private String dayType;
  private String screenClass;
  private String movieGoerAge;
  private String movieType;
  private double price;

  public TicketPriceBoundary() {
    s = new Scanner(System.in);
    q = new Scanner(System.in);
    int choice = 0;
  }

  public String setDayType() {
    System.out.print(
      "Please enter Day Type (H for Holiday or W for weekday) : "
    );
    dayType = s.nextLine().toUpperCase();
    if (dayType.startsWith("H")) {
      dayType = "HOLIDAY";
    }
    if (dayType.startsWith("W")) {
      dayType = "WEEKEND";
    }
    return dayType;
  }

  public String setScreenClass() {
    System.out.print(
      "Please enter ScreenClass (R for REGULAR_SCREEN, P for PLATINUM_MOVIE_SUITES): "
    );
    screenClass = s.nextLine().toUpperCase();
    if (screenClass.startsWith("R")) {
      screenClass = "REGULAR_SCREEN";
    }
    if (screenClass.startsWith("W")) {
      screenClass = "WEEKEND";
    }
    return screenClass;
  }

  public String setMovieGoerAge() {
    System.out.print(
      "Please enter MovieGoer Age Classification (A for ADULT, S FOR SENIOR, T FOR STUDENT): "
    );
    movieGoerAge = s.nextLine().toUpperCase();
    if (movieGoerAge.startsWith("A")) {
      movieGoerAge = "ADULT";
    }
    if (movieGoerAge.startsWith("S")) {
      movieGoerAge = "SENIOR";
    }
    if (movieGoerAge.startsWith("T")) {
      movieGoerAge = "STUDENT";
    }

    return movieGoerAge;
  }

  public String setMovieType() {
    System.out.print(
      "Please enter Movie Type Classification (B for BLOCKBUSTER, 3D FOR THREEDIMENSION, D FOR DOCUMENTARY): "
    );
    movieType = s.nextLine().toUpperCase();
    if (movieType.startsWith("B")) {
      movieType = "BLOCKBUSTER";
    }
    if (movieType.startsWith("3D")) {
      movieType = "THREEDIMENSION";
    }
    if (movieType.startsWith("D")) {
      movieType = "DOCUMENTARY";
    }

    return movieType;
  }

  public double setPrice() {
    System.out.print("Please enter Ticket Price : ");

    while (!s.hasNextDouble()) {
      System.out.println("Please enter a numeric value. \n");
      s.next();
    }
    price = s.nextDouble();
    return price;
  }

  public void printAddTicketPriceSuccessMessaage() {
    System.out.println(" TicketPrice has been added \n");
  }

  public void printUpdateTicketPriceSuccessMessaage() {
    System.out.println("TicketPrice has been updated \n");
  }

  public void printAllTicketPrices(ArrayList<TicketPrice> masterTicketPrices) {
    if (masterTicketPrices.size() > 0) {
      System.out.println("List of TicketPrices configured !");
      for (TicketPrice ticketPrice : masterTicketPrices) {
        System.out.println(
          String.format("| %-15s", ticketPrice.getDayType().toString()) +
          String.format("| %-22s", ticketPrice.getScreenClass().toString()) +
          String.format("| %-15s", ticketPrice.getMovieGoerAge().toString()) +
          String.format("| %-15s| ", ticketPrice.getMovieType().toString()) +
          ticketPrice.getPrice()
        );
      }
    }
    else {
      System.out.println("No Ticket Prices configured !");
    }
  }

  public void printInvalidDayType() {
    System.out.println("Day Type " + dayType + " is invalid. \n");
  }

  public void printInvalidScreenClass() {
    System.out.println("Screem Class " + screenClass + " is invalid. \n");
  }

  public void printInvalidMovieGoerAge() {
    System.out.println(
      "Movie Goer age classification " + movieGoerAge + " is invalid. \n"
    );
  }

  public void printInvalidMovieType() {
    System.out.println(
      "Movie Type classification " + movieType + " is invalid. \n"
    );
  }
}
