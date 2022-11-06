import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Scanner;

public class ShowManager {


    static Scanner sc = new Scanner(System.in);
    private HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> showDateDict; // showDateDict[showDate]: {showTime: [showID1, showID2, showID3, showID4...]}
    private Hashtable<String, Show> showIDDict; // {showID: show}
    private HashMap<String, ArrayList<String>> showCineplexDict; // showCineplexDict[CineplexID]: [showID1, showID2, showID3, showID4...]
    private Hashtable<String, String> showNameIDMap; // {showName: showID}
    >> varsha help pls public void addShowC(String showDate, String showTime, String movieID, String screenID, int emptySeats, int numofRows, int seatsPerRow) {

        ArrayList<String> showList = this.showDateDict.get(showDate);
        if (showList == null) // when showList is null
        {
            showList = new ArrayList<String>();
            Show show = new Show(movieID, showDate, showTime, screenID, emptySeats, numofRows, seatsPerRow);
            String showID = show.getShowID();
            showList.add(showID);

        }
        else { // when showList is not empty (null)
            if () // show with specified showTime and showDate does not exist in showDateDict[showDate][showTime]
            {
                showList = new ArrayList<String>();
                Show show = new Show(movieID, showDate, showTime, screenID, emptySeats, numofRows, seatsPerRow);


                String showID = show.getShowID();
                showList.add(showID);

            }
            else // show with specified showTime and showDate does not exist in showDateDict[showDate] hence throw showExistsException
            {
                throw new showExistsException("Show for movie "+ this.showName + " at " + this.showTime + " on " + this.showDate + " already exists. Unable to add a show on this date and time");
            }
        }

    }

    >> varsha help pls  public ArrayList<String> getShowListByDate(String showDate) {
        ArrayList<String> showList = new ArrayList<String>();
        for (ArrayList<Show>: this.show)
        return this.showDateDict.get(showDate);
    }

    >> varsha help pls public ArrayList<String> getShowListByDateTime(String showDate, String showTime) {
        return this.showDateDict.get(showDate);
    }


    public Show getShowByID(String showID) {
        return this.showIDDict.get(showID);
    }

    public void addShowB() {
        System.out.println("Enter Show Date: ");
        String date = sc.nextLine();
        System.out.println("Enter Show Time: ");
        String time = sc.next();
        System.out.println("Enter Movie ID: ");
        String movieID = sc.next();
        System.out.println("Enter Screen ID: ");
        String screenID = sc.next();
        System.out.println("Enter total seat capacity: ");
        int emptySeats = sc.nextInt();
        System.out.println("Enter number of rows: ");
        int numOfRows = sc.nextInt();
        System.out.println("Enter number of number of seats per row: ");
        int seatsPerRow = sc.nextInt();

        this.addShowC(date, time, movieID, screenID, emptySeats, numOfRows, seatsPerRow);
    }

    >> varsha help pls public void viewShow(ArrayList<Show> show) {
        for(Show s:show) {
            System.out.println(s.toString());
            System.out.println("---------------------------X---------------------------");
        }
    }

    >> varsha help pls public void showListing(ArrayList<Show> show) {
        System.out.println("List of Shows:");
        for(Show s: show) {
            System.out.println(s.getShowID()+" - "+s.getShowName()+" - "+s.getShowDate()+" - "+s.getShowTime());
        }
    }
    public Show searchShowC(String userInput_showName) {
        String userInput_showID = this.showNameIDMap.get(userInput_showName);
        Show show = this.getShowByID(userInput_showID);
        if (show != null && show.getShowName().equals(userInput_showName)) return show;
        else return null;
    }
    public void searchShowB() {
        System.out.println("Enter the show name you want to search for: ");
        String userInput_showName = sc.nextLine().toLowerCase();

        Show show = this.searchShowC(userInput_showName);
        if (show != null)
        {
            System.out.println("Show Found!");
            System.out.println(show.getShowID()+" - "+show.getShowName()+" - "+show.getShowDate()+" - "+show.getShowTime());
        }
        else {
            System.out.println("Show not Found!");
        }
    }
}
