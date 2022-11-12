import java.util.ArrayList;
import java.util.Scanner;

public class TicketPriceBoundary extends Boundary implements BaseManager {
    TicketPriceManager ticketPriceManager;

    public TicketPriceBoundary() {
    }

    @Override
    public void setManagers() {
      this.ticketPriceManager = this.getCentralManager().getTicketPriceMgr();
    }

    @Override
    public void setMasterLists() {

    }

    public String getDayType() {
        String dayType = this.getInputLine("Please enter Day Type (H for Holiday or W for weekday) : ");
        String type = null;

        if (dayType.startsWith("H")) {
            type = "HOLIDAY";
        }
        if (dayType.startsWith("W")) {
            type = "WEEKEND";
        }

        while (type == null) {
            type = this.getInputLine("Day Type " + dayType + " is invalid. \n");
        }

        return type;
    }

    public String getScreenClass() {
        String type = null;
        String screenClass = this.getInputLine("Please enter ScreenClass (R for REGULAR_SCREEN, P for PLATINUM_MOVIE_SUITES): ");
        if (screenClass.startsWith("R")) {
            type = "REGULAR_SCREEN";
        }
        if (screenClass.startsWith("P")) {
            type = "PLATINUM_MOVIE_SUITES";
        }

        while (type == null) {
          type = this.getInputLine("Screen Class " + screenClass + " is invalid.");
        }
        return type;
    }

    public String getMovieGoerAge() {
        String movieGoerAge = null;
        movieGoerAge = this.getInputLine("Please enter MovieGoer Age Classification (A for ADULT, S FOR SENIOR, T FOR STUDENT): ").toUpperCase();
        String age = null;
        if (movieGoerAge.startsWith("A")) {
            age = "ADULT";
        }
        if (movieGoerAge.startsWith("S")) {
            age = "SENIOR";
        }
        if (movieGoerAge.startsWith("T")) {
            age = "STUDENT";
        }

        while (age == null) {
            age = this.getInputLine("Movie Goer age classification " + movieGoerAge + " is invalid. ");
        }

        return age;
    }

    public String getMovieType() {
        String movieType = this.getInputLine("Please enter Movie Type Classification (B for BLOCKBUSTER, 3D FOR THREEDIMENSION, D FOR DOCUMENTARY): ").toUpperCase();
        String type = null;
        if (movieType.startsWith("B")) {
          type = "BLOCKBUSTER";
        }
        if (movieType.startsWith("3D")) {
          type = "THREEDIMENSION";
        }
        if (movieType.startsWith("D")) {
            type = "DOCUMENTARY";
        }

        while (type == null) {
            type = this.getInputLine("Movie Type classification " + movieType + " is invalid.");
        }

        return type;
    }

    public double getPrice() {
        return getInputDouble("Please enter Ticket Price : ");
    }

    public void printAllTicketPrices(ArrayList<TicketPrice> masterTicketPrices) {
        if (masterTicketPrices.size() > 0) {
            this.println("List of TicketPrices configured !");
            for (TicketPrice ticketPrice : masterTicketPrices) {
                this.println(
                        String.format("| %-15s", ticketPrice.getDayType().toString()) +
                                String.format("| %-22s", ticketPrice.getScreenClass().toString()) +
                                String.format("| %-15s", ticketPrice.getMovieGoerAge().toString()) +
                                String.format("| %-15s| ", ticketPrice.getMovieType().toString()) +
                                ticketPrice.getPrice()
                );
            }
        } else {
            this.println("No Ticket Prices configured !");
        }
    }

    public int getTicketPriceMenuChoice() {
        int choice = -1;
        choice = this.getInputInt(
                """

                        ========================= Welcome to Staff App =========================
                        1.  Add Ticket Price                                             \s
                        2.  List Ticket Prices                                             \s
                        3.  Return to Staff Menu                                             \s
                        ========================================================================
                        Enter choice:"""
        );

        return choice;
    }

    public void ticketPriceOperations() {
        int ticketPriceChoice = 0;
        while (ticketPriceChoice != 3) {
            ticketPriceChoice = this.getTicketPriceMenuChoice();
            if (ticketPriceChoice < 0 | ticketPriceChoice > 3) {
                this.println("Please only input integers between numbers 1 and 3 (inclusive)");
                continue;
            }
            switch (ticketPriceChoice) {
                case 1:
                    String dateType = this.getDayType();
                    String screenClass = this.getScreenClass();
                    String movieGoerAge = this.getMovieGoerAge();
                    String movieType = this.getMovieType();
                    double price = this.getPrice();

                    char type = this.ticketPriceManager.addTicketPrice(dateType, screenClass, movieGoerAge, movieType, price);
                    if (type == 'U') {
                      this.println("TicketPrice successfully updated.");
                    }
                    else {
                      this.println("TicketPrice successfully created");
                    }

                    break;
                case 2:
                    this.printAllTicketPrices(this.ticketPriceManager.listAllTicketPrices());
                    break;
                case 3:
                    break;
            }
        }
    }
}
