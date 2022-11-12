import java.util.ArrayList;
import java.util.Scanner;

public class ScreenBoundary extends Boundary implements BaseBoundary {
    ScreenManager screenManager;

    ArrayList<ScreenEY> masterScreens;

    public ScreenBoundary() {

    }

    public void setMasterArrays() {
        this.masterScreens = this.getCentralManager().getMasterScreens();
    }

    public String getCineplex() {
        return this.getInputLine("Please enter Cineplex Name: ");
    }


    public String getScreen() {
        return this.getInputLine("Please enter Screen Name: ");
    }

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

    public int getNumberOfRows() {
        return this.getInputInt("Please enter Number of Rows: ");
    }

    public int getSeatPerRow() {
        return this.getInputInt("Please enter Seats Per Row: ");
    }

    public void printAllScreens() {
        this.println("\n---------------------------X---------------------------\n");
        this.println("\nScreen List :  \n ");
        for (String line : this.screenManager.listAllScreens()) {
           this.println(line);
        }
        this.println("\n---------------------------X---------------------------\n");

    }

    public int getScreenMenuChoice() {
        int choice = -1;
        choice = this.getInputInt(
        """

                ========================= Welcome to Staff App =========================
                1.  Add Screen                                             \s
                2.  Search Screen                                             \s
                3.  List all Screens                                             \s
                4.  Return to Staff Menu                                             \s
                ========================================================================
                Enter choice:
                """
        );
        while (!(choice >= 1 && choice <= 4)) {
            choice = this.getInputInt("Please only enter integers between 1 to 4 (inclusive).");
        }
        return choice;
    }

    public void screenOperations () {
        int screenChoice = 0;
        while (screenChoice != 4) {
            screenChoice = this.getScreenMenuChoice();
            switch (screenChoice) {
                case 1:
                    String cineplexName = this.getCineplex();
                    String screenName = this.getScreen();
                    String screenClass = this.getScreenClass();
                    int numRows = this.getNumberOfRows();
                    int SeatPerRow = this.getSeatPerRow();
                    int success = this.screenManager.addScreen(cineplexName, screenName, screenClass, numRows, SeatPerRow);
                    if (success == -1) {
                        this.println("Screen cannot be added as Cineplex " + cineplexName + "is not found.");
                    }
                    else if (success == 0) {
                        this.println("Screen " + screenName + " cannot be added as it already exists.");
                    }
                    else {
                        this.println("Screen " + screenName + " has been added to Cineplex " + cineplexName + " successfully");
                    }
                    break;
                case 2:
                    ScreenEY screen = this.screenManager.searchScreen(this.getScreen());
                    this.println(screen.viewDetails());
                    break;
                case 3:
                    this.printAllScreens();
                    break;
                case 4:
                    break;
            }
        }
    }

    @Override
    public void setManagers() {
        this.screenManager = this.getCentralManager().getScreenMgr();
    }

    @Override
    public void setBoundaries() {

    }
}
