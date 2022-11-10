import java.util.ArrayList;

public class CineplexManager implements Manager{
    private ArrayList<User> masterUserList;
    private ArrayList<Cineplex> masterCineplexes;
    private ArrayList<Screen> masterScreens;
    private ArrayList<Booking> masterBookings;
    private ArrayList<Show> masterShows;
    private ArrayList<Movie> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ViewerRatings> masterRatings;
    
    private CineplexBoundary cineplexIO;

   
    public CineplexManager(CineplexBoundary cineplexB) {
        cineplexIO = cineplexB;
    }

    @Override
    public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<Cineplex> masterCineplexes,
    ArrayList<Screen> masterScreens,
    ArrayList<Booking> masterBookings,
    ArrayList<Show> masterShows,
    ArrayList<Movie> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ViewerRatings> masterRatings) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;
  }

    public Cineplex getCineplexByID(String cineplexID) {
        for(Cineplex c: masterCineplexes) {
            if(cineplexID.equals(c.getCineplexID()))
                return c;
            else
                System.out.println("No cineplex exists with this ID!");
        }
        return null;
    }
    
    public void addCineplex() {
        String name=cineplexIO.setName();
        String location=cineplexIO.setLocation();
        String cineplexID = "";
        ArrayList<String> screenID = new ArrayList<String>();

        for(Cineplex c: masterCineplexes) {
            if(c.getName().equals(name))
            {
                cineplexIO.printCineplexDuplicateError();
                return;
            }
        }
         
        Cineplex c=new Cineplex(cineplexID, name,location,screenID);
        masterCineplexes.add(c);
        cineplexIO.printAddCineplexSuccessMessaage();

    }
    
    public void searchCineplex() {
        String name=cineplexIO.setName();

        boolean cineplexFound = false;
        Cineplex matchingCineplex=null;
        for(Cineplex c: masterCineplexes) {
            if(c.getName().startsWith(name))
            {
                cineplexFound=true;
                matchingCineplex = c;
                break;
            }
        }
        if (cineplexFound) {
            cineplexIO.printCineplexFoundMessaage(matchingCineplex);
        }
        else {
            cineplexIO.printCineplexNotFoundMessaage();
        }
    }

    public ArrayList<Cineplex> getMasterCineplexes() {
        return masterCineplexes;
    }

    public String convertIDX2CineplexID(int idx) {
        return masterCineplexes.get(idx).getCineplexID();
    }
	
}
