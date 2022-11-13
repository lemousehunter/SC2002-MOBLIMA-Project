package Boundary;

import Controller.TicketPriceManager;

import java.util.ArrayList;

/**
 * A Ticket Price Boundary.Boundary Object
 * 
 * <p>
 * A <code>Boundary.TicketPriceBoundary</code> object used
 * process all ticket price input and output
 * </p>
 * 
 */


public class TicketPriceBoundary extends Boundary implements BaseBoundary {
    /**
     * contains ticket price manager object to process all ticket price objects
     */
    TicketPriceManager ticketPriceManager;

    /**
     * Constructor to instantiate Boundary.TicketPriceBoundary object
     */
    public TicketPriceBoundary() {
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.ticketPriceManager = this.getCentralManager().getTicketPriceMgr();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBoundaries() {

    }

    /**
     * Method to get day type from user
     * @return day type (HOLIDAY or WEEKDAY)
     */
    public String getDayType() {
        String dayType = "";
        while (true) {
            dayType = this.getInputLine("\nPlease enter Day Type (H for Holiday or W for weekday) : ");
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

    /**
     * Method to get Screen Class from user
     * @return type of screen class (REGULAR_SCREEN or PLATINUM_MOVIE_SUITES)
     */
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

    /**
     * Method to get movie goer age group from user
     * @return the movie goer age group (ADULT,SENIOR or STUDENT)
     */
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

    /**
     * Method to get movie type from user
     * @return the type of movie (BLOCKBUSTER,THREEDIMENSION or DOCUMENTARY)
     */
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

    /**
     * Method to get ticket price from user
     * @return The ticket price
     */
    public double getPrice() {
        return getInputDouble("Please enter Ticket Price : ");
    }

    /**
     * Method to print all ticket 
     * @param printPrices The arraylist of ticket prices
     */
    public void printAllTicketPrices(ArrayList<String> printPrices) {
        if (printPrices.size() > 0) {
            for (String printline : printPrices) {
                this.println(printline);
            }
        } else {
            this.println("No Ticket Prices configured !");
        }
    }

    /**
     * Method to obtain user choice from a printed menu     * 
     * @return The user selection value
     */
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

    /**
     * Method to perform operations based on ticket price menu choice
     * (add ticket price,list ticket price,return to staff menu)
     */
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
                        this.println("\nEntity.TicketPrice successfully updated.");
                    } else {
                        this.println("\nEntity.TicketPrice successfully created");
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
