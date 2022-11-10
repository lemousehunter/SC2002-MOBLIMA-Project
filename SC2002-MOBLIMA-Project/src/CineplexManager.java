import java.util.ArrayList;

public class CineplexManager implements Manager{
    private ArrayList<User> masterUserList;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ReviewE> masterRatings;
    private ArrayList<ShowSeatEY> masterShowSeats;
    
    private CineplexBoundary cineplexIO;

   
    public CineplexManager(CineplexBoundary cineplexB) {
        cineplexIO = cineplexB;
    }

    @Override
    public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<CineplexEY> masterCineplexes,
    ArrayList<ScreenEY> masterScreens,
    ArrayList<BookingEY> masterBookings,
    ArrayList<ShowEY> masterShows,
    ArrayList<MovieEY> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ReviewE> masterRatings,
    ArrayList<ShowSeatEY> masterShowSeats
    ) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;
    this.masterShowSeats = masterShowSeats;
  }

    public CineplexEY getCineplexByID(String cineplexID) {
        for(CineplexEY c: masterCineplexes) {
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

        for(CineplexEY c: masterCineplexes) {
            if(c.getName().equals(name))
            {
                cineplexIO.printCineplexDuplicateError();
                return;
            }
        }
         
        CineplexEY c=new CineplexEY(cineplexID, name,location,screenID);
        masterCineplexes.add(c);
        cineplexIO.printAddCineplexSuccessMessaage();

    }
    
    public void searchCineplex() {
        String name=cineplexIO.setName();

        boolean cineplexFound = false;
        CineplexEY matchingCineplex=null;
        for(CineplexEY c: masterCineplexes) {
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

    public ArrayList<CineplexEY> getMasterCineplexes() {
        return masterCineplexes;
    }

    public String convertIDX2CineplexID(int idx) {
        return masterCineplexes.get(idx).getCineplexID();
    }
	
}
