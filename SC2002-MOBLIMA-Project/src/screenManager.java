import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class ScreenManager implements Manager {
	private ArrayList<User> masterUserList;
    private ArrayList<Cineplex> masterCineplexes;
    private ArrayList<Screen> masterScreens;
    private ArrayList<Booking> masterBookings;
    private ArrayList<Show> masterShows;
    private ArrayList<Movie> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ViewerRatings> masterRatings;
    private ScreenBoundary screenIO;

	public ScreenManager() {
        screenIO = new ScreenBoundary();
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

	    public Screen getScreenByID(String screenID) {
	        for(Screen s:this.masterScreens) {
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
			for(Cineplex c: masterCineplexes) {
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
	
			for(Screen s: masterScreens) {
				if(s.getScreenName().equalsIgnoreCase(name))
				{
					screenIO.printScreenDuplicateError();
					return;
				}
			}
			 
			Screen s=new Screen(cineplexID,name,screenClass,numRows,seatsPerRow);
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
			Screen matchingScreen=null;
			for(Screen s: masterScreens) {
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
