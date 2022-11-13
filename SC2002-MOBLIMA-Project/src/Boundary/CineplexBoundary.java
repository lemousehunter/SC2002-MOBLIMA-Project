package Boundary;

import Controller.CineplexManager;
import Entity.CineplexEY;

import java.util.Arrays;
/**
 * A Boundary.CineplexBoundary Object
 * 
 * <p>
 * A <code>Boundary.CineplexBoundary</code> object contains all the methods and attributes
 * required for interfacing of cinplexes
 * </p>
 */
public class CineplexBoundary extends Boundary implements BaseBoundary{
    /**
     * Object of Controller.CineplexManager
     */
    CineplexManager cineplexMgr;

    /**
     * Default Constructor
     */
    public CineplexBoundary() {
        
    }

    /**
     * Method to set cineplexMgr
     */
    @Override
    public void setManagers() {
        this.cineplexMgr = this.getCentralManager().getCineplexMgr();
    }

    /**
     * Method to set boundaries
     */
    @Override
    public void setBoundaries() {

    }

    /**
     * Method for printing the details of a particular cineplex
     * @param cineplexID The ID of the cineplex to print
     **/
    public void viewDetails(String cineplexID) {
        CineplexEY cineplex = this.cineplexMgr.getCineplexByID(cineplexID);
        this.println("Cineplex cineplexID = " + cineplexID + " , name = " + cineplex.getName() + " , location = " + cineplex.getLocation() + " , screens = "
                + Arrays.toString(cineplex.getScreenNames().toArray()));
    }

    
    /** 
     * Method to ask for name of the cineplex
     * @return Print message asking for input from user
     */
    public String getName() {
        return this.getInputLine("Please enter Cineplex Name: ");
    }

    
    /** 
     * Method to ask for location of a particular cineplex from the user
     * @param cineplexName Name of the cineplex
     * @return Print message asking for input from user
     */
    public String getLocation(String cineplexName) {
        return this.getInputLine("Please enter " + cineplexName + "  Location: ");
    }

    /**
     * Method to search for a particular cineplex
     */
    public void searchCineplex() {
        String cineplexName = this.getInputLine("Please enter the name of the cineplex you would like to search: ");
        cineplexName = this.getScanner().nextLine();

        CineplexEY cineplex = this.cineplexMgr.searchCineplexByName(cineplexName);
        if (cineplex == null) {
            this.println("\nCineplex " + cineplexName + " not found!");
        }
        else {
            this.println("\nFound Cineplex  : " + cineplex.getName() + " at location " + cineplex.getLocation());
        }
    }

    /**
     * Method to print all cineplexes
     */
    public void printAllCineplexes() {
        this.println("\n---------------------------X---------------------------\n");
        this.println("\nCineplex List : \n ");
        for (String line : this.cineplexMgr.listAllCineplexes()) {
            this.println(line);
        }
        this.println("\n---------------------------X---------------------------\n");
    }

    
    /** 
     * Method to ask for user input for accessing menu of cineplexes
     * @return choice
     */
    public int getCineplexMenuChoice() {
        int choice = -1;
        choice = this.getInputInt(
            "\n========================= Welcome to Staff App =========================\n" +
            "1.  Add Cineplex                                              \n" +
            "2.  Search Cineplex                                              \n" +
            "3.  List all Cineplexes                                              \n" +
            "4.  Return to Staff Menu                                              \n" +
            "========================================================================\n" +
            "Enter Choice : "
            );
        while (choice < 1) {
            choice = this.getInputInt("Please enter an integer value. \n");
        }

        return choice;
    }

    /**
     * Method to display cineplex menu and ask for user input
     */
    public void cineplexOperations() {
        int cineplexChoice = 0;
        while (cineplexChoice != 4) {
            cineplexChoice = this.getCineplexMenuChoice();
            if (cineplexChoice < 0 | cineplexChoice > 4) {
                this.println("Enter choice betwen 1-4 values only \n");
                continue;
            }
            switch (cineplexChoice) {
                case 1:
                    String cineplexName = this.getName();
                    cineplexName = this.getScanner().nextLine();
                    String location = this.getLocation(cineplexName);
                    
                    Boolean success = this.cineplexMgr.addCineplex(cineplexName, location);
                    if (success) {
                        this.println("\nCineplex " + cineplexName + " has been added !");
                    }
                    else {
                        this.println("\nError: " + cineplexName + " already exists");
                    }
                    break;
                case 2:
                    this.searchCineplex();
                    break;
                case 3:
                    this.printAllCineplexes();
                    break;
                case 4:
                    break;
            }
        }
    }
}
