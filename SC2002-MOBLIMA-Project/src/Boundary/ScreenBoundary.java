package Boundary;

import Controller.ScreenManager;
import Entity.ScreenEY;

import java.util.ArrayList;

/**
 * A ScreenBoundary object that extends Boundary and implements BaseBoundary interface 
 * 
 * <p>
 * A <code>ScreenBoundary</code> object used to process all 
 * Screen object related input and output
 * </p>
 */
public class ScreenBoundary extends Boundary implements BaseBoundary {
    /**
     * contains the screenManager to process all screen related objects
     */
    ScreenManager screenManager;

    /**
     * The master list of screenEY objects
     */
    ArrayList<ScreenEY> masterScreens;

    /**
     * Constructor for ScreenBoundary
     */
    public ScreenBoundary() {

    }

    /**
     * Method to set master screen
     */
    public void setMasterArrays() {
        this.masterScreens = this.getCentralManager().getMasterScreens();
    }

    /**
     * Method to get cineplex name from user
     * @return The cineplex name
     */
    public String getCineplex() {
        return this.getInputLine("Please enter Cineplex Name: ");
    }


    /**
     * Method to get screen name from user
     * @return The screen name
     */
    public String getScreen() {
        return this.getInputLine("Please enter Screen Name: ");
    }

    /**
     * Method to get screen class from user
     * @return The screen type 
     */
    public String getScreenClass() {
        String screenClass;
        while (true) {
            screenClass = this.getInputLine("Please enter ScreenClass (R for REGULAR_SCREEN, P for PLATINUM_MOVIE_SUITES): ");
            if (screenClass.equals("R")) {
                screenClass = "REGULAR_SCREEN";
                break;
            } else if (screenClass.equals("P")) {
                screenClass = "PLATINUM_MOVIE_SUITES";
                break;
            } else {
                this.println("Invalid input. Please enter only 'R' or 'P'.");
            }
        }
        return screenClass;
    }

    /**
     * Method to get number of rows from user
     * @return The number of rows
     */
    public int getNumberOfRows() {
        return this.getInputInt("Please enter Number of Rows: ");
    }

    /**
     * Method to get number of seat per row from user
     * @return The number of seats per row 
     */
    public int getSeatPerRow() {
        return this.getInputInt("Please enter Seats Per Row: ");
    }

    /**
     * Method to print all screens
     */
    public void printAllScreens() {
        this.println("\n---------------------------X---------------------------\n");
        this.println("\nScreen List :  \n ");
        for (String line : this.screenManager.listAllScreens()) {
           this.println(line);
        }
        this.println("\n---------------------------X---------------------------\n");

    }

    /**
     * Method to get choice from screen menu
     * @return The choice
     */
    public int getScreenMenuChoice() {
        int choice = -1;
        choice = this.getInputInt(
            "\n========================= Welcome to Staff App =========================\n" +
            "1.  Add Screen                                              \n" +
            "2.  Search Screen                                              \n" +
            "3.  List all Screens                                              \n" +
            "4.  Return to Staff Menu                                              \n" +
            "========================================================================\n" +
            "Enter choice: "

        );
        while (!(choice >= 1 && choice <= 4)) {
            choice = this.getInputInt("Please only enter integers between 1 to 4 (inclusive).");
        }
        return choice;
    }

  
    /**
     * Method to perform screen operations base on screen menu choice
     */
    public void screenOperations () {
        int screenChoice = 0;
        while (screenChoice != 4) {
            screenChoice = this.getScreenMenuChoice();
            switch (screenChoice) {
                case 1:
                    String cineplexName = this.getCineplex();
                    cineplexName=this.getScanner().nextLine();
                    String screenName = this.getScreen();
                    String screenClass = this.getScreenClass();
                    int numRows = this.getNumberOfRows();
                    int SeatPerRow = this.getSeatPerRow();
                    int success = this.screenManager.addScreen(cineplexName, screenName, screenClass, numRows, SeatPerRow);
                    if (success == -1) {
                        this.println("\nError : Screen cannot be added as Cineplex " + cineplexName + " is not found.");
                    }
                    else if (success == 0) {
                        this.println("\nError : Screen " + screenName + " cannot be added as it already exists.");
                    }
                    else {
                        this.println("\nScreen " + screenName + " has been added to Cineplex " + cineplexName + " successfully");
                    }
                    break;
                case 2:
                    screenName = this.getScreen();
                    ScreenEY screen = this.screenManager.searchScreen(screenName);
                    if (screen != null) {
                        this.println(screen.viewDetails());
                    }
                    else {
                        this.println("No screen matching "+ screenName + " was found.");
                    }
                    break;
                case 3:
                    this.printAllScreens();
                    break;
                case 4:
                    break;
            }
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.screenManager = this.getCentralManager().getScreenMgr();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBoundaries() {

    }
}
