import java.util.Arrays;

public class CineplexBoundary extends Boundary implements BaseBoundary{
    CineplexManager cineplexMgr;

    public CineplexBoundary() {
        
    }

    @Override
    public void setManagers() {
        this.cineplexMgr = this.getCentralManager().getCineplexMgr();
    }

    @Override
    public void setBoundaries() {

    }

    /**
     * Method for printing the details of the cineplex for printing on the screen
     *
     **/
    public void viewDetails(String cineplexID) {
        CineplexEY cineplex = this.cineplexMgr.getCineplexByID(cineplexID);
        this.println("Cineplex cineplexID = " + cineplexID + " , name = " + cineplex.getName() + " , location = " + cineplex.getLocation() + " , screens = "
                + Arrays.toString(cineplex.getScreenNames().toArray()));
    }

    public String getName() {
        return this.getInputLine("Please enter Cineplex Name: ");
    }

    public String getLocation(String cineplexName) {
        return this.getInputLine("Please enter " + cineplexName + "  Location: ");
    }

    public void searchCineplex() {
        String cineplexName = this.getInputLine("Please enter the name of the cineplex you would like to search for.");
        CineplexEY cineplex = this.cineplexMgr.searchCineplexByName(cineplexName);
        if (cineplex == null) {
            this.println("\nCineplex " + cineplexName + " not found!");
        }
        else {
            this.println("\nFound Cineplex  : " + cineplex.getName() + " at location " + cineplex.getLocation());
        }
    }

    public void printAllCineplexes() {
        this.println("\n---------------------------X---------------------------\n");
        this.println("\nCineplex List : \n ");
        for (String line : this.cineplexMgr.listAllCineplexes()) {
            this.println(line);
        }
        this.println("\n---------------------------X---------------------------\n");
    }

    public int getCineplexMenuChoice() {
        int choice = -1;
        choice = this.getInputInt("""
                        ========================= Welcome to Staff App =========================
                        1.  Add Cineplex                                             \s
                        2.  Search Cineplex                                             \s
                        3.  List all Cineplexes                                             \s
                        4.  Return to Staff Menu                                             \s
                        ========================================================================
                        Enter Choice:
                        """);
        while (choice < 1) {
            choice = this.getInputInt("Please enter an integer value. \n");
        }

        return choice;
    }

    private void cineplexOperations() {
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
                    String location = this.getLocation(cineplexName);
                    Boolean success = this.cineplexMgr.addCineplex(cineplexName, location);
                    if (success) {
                        this.println("\nCineplex " + cineplexName + " has been added !");
                    }
                    else {
                        this.println("\nError. " + cineplexName + " already exists");
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
