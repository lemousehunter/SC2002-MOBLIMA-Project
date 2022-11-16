package App;

import Boundary.LoginBoundary;
import Entity.CentralManagerEY;
import Entity.MovieGoerEY;
import Entity.StaffEY;

import java.text.ParseException;
import java.util.*;
import java.io.IOException;
/**
* Moblima App 
* 
* 
*/
public class MoblimaApp {

    /**
     * A CentralManagerEY object variable
     */
    CentralManagerEY centralManager;

    /**
     * Input object to get user input (with wrapped validation)
     */
    Input input;

    LoginBoundary loginBoundary;

    /**
     * Constructor for Moblima App
     */
    public MoblimaApp() {
        this.input = new Input(null);
    }

    /**
     * Main method
     * @param args Stores arguments passed
     * @throws IOException If There's read/write error
     */
    public static void main(String[] args) throws IOException {

        MoblimaApp moblimaApp = new MoblimaApp();
        moblimaApp.process(args);
        System.out.println("Moblima Application is Terminating ...");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    System.out.println("Writing all  Master files");

                    // call all entity managers write method to write to files from the master array
                    // lists
                    moblimaApp.writeDataFiles();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * Method to display startup user menu and get choice
     * @param args stores args passed
     * @throws IOException If there read/write error
     */
    public void process(String[] args) throws IOException {
        try {

            // Instantiate CentralManager
            centralManager = new CentralManagerEY();
            this.loginBoundary = this.centralManager.getLoginBoundary();

            System.out.println("""
                    ==================== Welcome to Moblima Application ====================
                     1. Staff  Application
                     2. User   Application
                     3. Exit
                    ========================================================================""");
            int choice = this.input.getInt("Enter choice: ");
            switch (choice) {
                case 1:
                    ManageStaffApp();
                    break;

                case 2:
                    ManageMovieGoerApp();
                    break;

                case 3:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Method to write data to files
     * @throws IOException If there's write error
     */
    private void writeDataFiles() throws IOException {
        this.centralManager.getHolidayMgr().writeHolidays();
        this.centralManager.getCineplexMgr().writeCineplex();
        this.centralManager.getScreenMgr().writeScreen();
        this.centralManager.getMovieMgr().writeMovie();
        this.centralManager.getShowMgr().writeShow();
        this.centralManager.getBookingMgr().writeBookings();
        this.centralManager.getReviewMgr().writeViewerRatings();
        this.centralManager.getUserMgr().writeUser();
        this.centralManager.getTicketPriceMgr().writeTicketPrice();
    }

	/**
     * Method to manage staff menu
     */
    private void ManageStaffApp() {
        int choice = -1;
        do {
            System.out.println("""
                    ========================= Welcome to Staff App =========================
                    1. Register
                    2. Login
                    3. Exit Moblima App
                    ========================================================================""");
            choice = this.input.getInt("Enter choice: ");
            while (choice == -1) {
                choice = this.input.getInt("Please enter an integer value.");
            }
            switch (choice) {
                case 1:
                    this.loginBoundary.registerStaff();
                    break;

                case 2:
                    if (this.loginBoundary.getLoginSuccessStaff()) {
                        this.centralManager.getStaffBoundary().staffOperations();
                        // force user app to terminate by setting choice to 3
                        choice = 3;
                    }
                    break;

                case 3: // return to mainMenu
                    break;
            }
        } while (choice < 3);
    }

    /**
     * Method to manage MovieGoer menu
     * @throws ParseException
     */
    private void ManageMovieGoerApp() throws ParseException {
        int choice = -1;
        boolean userLoggedin = false;
        MovieGoerEY movieGoer = null;
        do {
            System.out.println("""

                    ========================= Welcome to User  App =========================
                    1. Register
                    2. Login
                    3. Exit Moblima App
                    ========================================================================""");
            choice = this.input.getInt("Enter choice: ");
            switch (choice) {
                case 1:
                    this.loginBoundary.registerMovieGoer();
                    break;
                case 2:
                    movieGoer = this.loginBoundary.getLoginSuccessMovieGoer();
                    if (movieGoer !=  null) {
                        this.centralManager.getMovieGoerBoundary().MovieGoerOperations(movieGoer.getUserID());
                        // force user app to terminate by setting choice to 3
                        choice = 3;
                    }
                    break;
                case 3: // return to mainMenu
                    break;
            }
        } while (choice < 3);
    }

}
