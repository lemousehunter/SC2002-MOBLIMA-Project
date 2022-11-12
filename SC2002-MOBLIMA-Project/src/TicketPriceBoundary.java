import java.util.ArrayList;

public class TicketPriceBoundary extends Boundary implements BaseBoundary {
    TicketPriceManager ticketPriceManager;

    public TicketPriceBoundary() {
    }

    @Override
    public void setManagers() {
        this.ticketPriceManager = this.getCentralManager().getTicketPriceMgr();
    }

    @Override
    public void setBoundaries() {

    }

    public String getDayType() {

        boolean firsttime = true;
        String dayType = "";
        while (true) {
            dayType = this.getInputLine("\nPlease enter Day Type (H for Holiday or W for weekday) : ");
            if (firsttime) {
                dayType = this.getScanner().nextLine();
                firsttime = false;
            }
            if (dayType.toUpperCase().startsWith("H")) {
                dayType = "HOLIDAY";
                break;
            }
            if (dayType.toUpperCase().startsWith("W")) {
                dayType = "WEEKEND";
                break;
            }
            this.println("Day Type " + dayType + " is invalid. \n");
        }

        return dayType;
    }

    public String getScreenClass() {
        String screenClass = "";
        while (true) {
            screenClass = this
                    .getInputLine("Please enter ScreenClass (R for REGULAR_SCREEN, P for PLATINUM_MOVIE_SUITES): ");
            if (screenClass.toUpperCase().startsWith("R")) {
                screenClass = "REGULAR_SCREEN";
                break;
            }
            if (screenClass.toUpperCase().startsWith("P")) {
                screenClass = "PLATINUM_MOVIE_SUITES";
                break;
            }
            this.println("Screen Class " + screenClass + " is invalid. \n");


        }
        return screenClass;
    }

    public String getMovieGoerAge() {
        String movieGoerAge = "";
        while (true) {

            movieGoerAge = this.getInputLine("Please enter MovieGoer Age Classification (A for ADULT, S FOR SENIOR, T FOR STUDENT): ").toUpperCase();

            if (movieGoerAge.startsWith("A")) {
                movieGoerAge = "ADULT";
                break;
            }
            if (movieGoerAge.startsWith("S")) {
                movieGoerAge = "SENIOR";
                break;
            }
            if (movieGoerAge.startsWith("T")) {
                movieGoerAge = "STUDENT";
                break;
            }

            this.println("Movie Goer age classification " + movieGoerAge + " is invalid. ");
        }
        return movieGoerAge;
    }

    public String getMovieType() {
        String movieType = "";
        while (true) {
        movieType = this.getInputLine(
                "Please enter Movie Type Classification (B for BLOCKBUSTER, 3D FOR THREEDIMENSION, D FOR DOCUMENTARY): ")
                        .toUpperCase();
            if (movieType.startsWith("B")) {
                movieType = "BLOCKBUSTER";
                break;
            }
            if (movieType.startsWith("3D")) {
                movieType = "THREEDIMENSION";
                break;
            }
            if (movieType.startsWith("D")) {
                movieType = "DOCUMENTARY";
                break;
            }
            movieType = this.getInputLine("Movie Type classification " + movieType + " is invalid.");
        }

        return movieType;
    }

    public double getPrice() {
        return getInputDouble("Please enter Ticket Price : ");
    }

    public void printAllTicketPrices(ArrayList<String> printPrices) {
        if (printPrices.size() > 0) {
            for (String printline : printPrices) {
                this.println(printline);
            }
        } else {
            this.println("No Ticket Prices configured !");
        }
    }

    public int getTicketPriceMenuChoice() {
        int choice = -1;
        choice = this.getInputInt(
                "\n========================= Welcome to Staff App =========================\n" +
                        "1.  Add Ticket Price                                              \n" +
                        "2.  List Ticket Prices                                              \n" +
                        "3.  Return to Staff Menu                                              \n" +
                        "========================================================================\n" +
                        "Enter choice: ");

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

                    char type = this.ticketPriceManager.addTicketPrice(dateType, screenClass, movieGoerAge, movieType,
                            price);
                    if (type == 'U') {
                        this.println("\nTicketPrice successfully updated.");
                    } else {
                        this.println("\nTicketPrice successfully created");
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
