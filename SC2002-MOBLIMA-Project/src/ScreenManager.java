import java.util.ArrayList;

public class ScreenManager extends Manager implements BaseManager {
	private ArrayList<User> masterUserList;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ReviewEY> masterRatings;
    private ScreenBoundary screenIO;

	public ScreenManager() {
        screenIO = new ScreenBoundary();
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
    ArrayList<ReviewEY> masterRatings) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;
  }

	    public ScreenEY getScreenByID(String screenID) {
	        for(ScreenEY s:this.masterScreens) {
	            if(screenID.equals(s.getScreenID()))
	                return s;
	            else
	                System.out.println("No screen exists with this ID!");
	        }
	        return null;
	    }
	    
	    public void addScreen() {
			String cineplex=screenIO.setCineplex();
			String name=screenIO.setScreen();
			String screenClass = screenIO.setScreenClass();
			int numRows=screenIO.setNumberOfRows();
			int seatsPerRow = screenIO.setSeatPerRow();
			String cineplexID = "";
			
			boolean cineplexFound = false;
			for(CineplexEY c: masterCineplexes) {
				if(c.getName().equals(cineplex))
				{
					cineplexFound = true;
					break;
				}
			}
			if (!cineplexFound){
				screenIO.printCineplexNotFound();
				return;
			}
	
			for(ScreenEY s: masterScreens) {
				if(s.getScreenName().equalsIgnoreCase(name))
				{
					screenIO.printScreenDuplicateError();
					return;
				}
			}
			 
			ScreenEY s=new ScreenEY(cineplexID,name,screenClass,numRows,seatsPerRow);
			masterScreens.add(s);
			screenIO.printAddScreenSuccessMessaage();

	    }
	    
	    // public void viewScreens() {
	    //     for(Screen s: masterScreens) {
	    //         s.viewDetails();
	    //         System.out.println("---------------------------X---------------------------");
	    //     }
	    // }

	    // public void screenListing() {
	    //     System.out.println("List of Screens:");
	    //     for(Screen s:masterScreens) {
	    //         System.out.println(s.getScreenName());
	    //     }
	    //}
		public void listAllScreens() {
			screenIO.printAllScreens(masterScreens);
		}

		public void searchScreen() {
			String name=screenIO.setScreen();
	
			boolean screenFound = false;
			ScreenEY matchingScreen=null;
			for(ScreenEY s: masterScreens) {
				if(s.getScreenName().equalsIgnoreCase(name))
				{
					screenFound=true;
					matchingScreen = s;
					break;
				}
			}
			if (screenFound) {
				screenIO.printScreenFoundMessaage(matchingScreen);
			}
			else {
				screenIO.printCinemaNotFoundMessaage();
			}
		}
	   
	}
