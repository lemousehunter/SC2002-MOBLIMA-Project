import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class cineplexManager implements Manager{


    static Scanner sc = new Scanner(System.in);
    private ArrayList<User> masterUserList;
    private ArrayList<Cineplex> masterCineplexes;
    private ArrayList<Screen> masterScreens;
    private ArrayList<Booking> masterBookings;
    private ArrayList<Show> masterShows;
    private ArrayList<Movie> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ViewerRatings> masterRatings;
   
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
    	String cineplexID = UUID.randomUUID().toString();
        System.out.println("Enter Cineplex Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Location: ");
        String loc = sc.next();
         
        Cineplex c=new Cineplex(cineplexID, name,loc);
        masterCineplexes.add(c);
    }
    
    public void viewCineplex() {
        for(Cineplex c: masterCineplexes) {
            System.out.println(c.viewDetails());
            System.out.println("---------------------------X---------------------------");
        }
    }

    public void cineplexListing() {
        System.out.println("List of Cineplex:");
        for(Cineplex c: masterCineplexes) {
            System.out.println(c.getName());
        }
    }

    public void searchCineplex() {
        System.out.println("Enter the cineplex name you want to search for: ");
        String user_input = sc.nextLine();
        boolean flag = false;
        for(Cineplex c:masterCineplexes) {
            if(c.getName().equalsIgnoreCase(user_input)) {
                flag = true;
                break;
            }
        }
        if(flag)
            System.out.println("Cineplex Found!");
        else
            System.out.println("Cineplex not found!");
    }
	
}
