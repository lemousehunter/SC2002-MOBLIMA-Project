import java.util.Arrays;

public class CineplexBoundary extends Boundary {
    private String cineplexName;
    private String cineplexLocation;

    CineplexManager cineplexMgr;

    public CineplexBoundary(CineplexManager cineplexMgr) {
        this.cineplexMgr = cineplexMgr;
    }

    /**
     * Method for printing the details of the cineplex for printing on the screen
     *
     **/
    public void viewDetails(String cineplexID) {
        CineplexEY cineplex = cineplexMgr.getCineplexByID(cineplexID);
        println("Cineplex cineplexID = " + cineplexID + " , name = " + cineplex.getName() + " , location = " + cineplex.getLocation() + " , screens = "
                + Arrays.toString(cineplex.getScreenNames().toArray()));
    }

    public void printCineplexDuplicateError() {
        this.println("Error. " + cineplexName + " already exists");
    }

    public String setName() {
        System.out.print("Please enter Cineplex Name: ");
        cineplexName = this.sc.nextLine();
        return cineplexName;
    }

    public String setLocation() {
        System.out.print("Please enter " + cineplexName + "  Location: ");
        cineplexLocation = this.sc.nextLine();
        return cineplexLocation;
    }


    public void printAddCineplexSuccessMessaage() {
        this.println("Cineplex " + cineplexName + " has been added !");
    }


    public void printCineplexFoundMessaage(CineplexEY cineplex) {
        this.println("Found Cineplex  : " + cineplex.getName() + " at location " + cineplex.getLocation());
    }


    public void printCineplexNotFoundMessaage() {
        this.println("Cineplex " + cineplexName + " not found!");
    }


    public void printAllCineplexes() {
        int idx = 1;
        this.println("\n---------------------------X---------------------------\n");
        this.println("\nCineplex List : \n ");
        for (CineplexEY c : this.cineplexMgr.getMasterCineplexes()) {
            this.println(idx + ": " + c.viewDetails());
            idx++;
        }
        this.println("\n---------------------------X---------------------------\n");
    }
}
