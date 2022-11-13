package Controller;

import Entity.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

	/**
	 * A Controller.ShowManager object that extends Controller.Manager object
	 * and implements Controller.BaseManager interface
	 * 
	 * <p>
	 * A <code>Controller.ShowManager</code> object used to process
	 * all show related information
	 * </p>
	 * 
	 * 
	 */


public class ShowManager extends Manager implements BaseManager {

    private CentralManagerEY centralManager;

    // MasterLists
    private ArrayList<ShowEY> masterShows;

    // managers
    private IoManager ioManager;

    /**
     * contains screen manager object to process screen objects
     */
    ScreenManager screenMgr;
    /**
     * contains movie manager object to process movie objects
     */
    MovieManager movieMgr;
    /**
     * contains cineplex manager object to process cineplex objects
     */
    CineplexManager cineplexMgr;

    /**
     * Constructor for Controller.ShowManager object
     */
    public ShowManager() {

    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        screenMgr = this.getCentralManager().getScreenMgr();
        movieMgr = this.getCentralManager().getMovieMgr();
        cineplexMgr = this.getCentralManager().getCineplexMgr();
        this.ioManager = this.getCentralManager().getIoManager();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setMasterLists() {
        CentralManagerEY centralMgr = this.getCentralManager();
        this.masterShows = centralMgr.getMasterShows();
    
    }

    /**
     * Method to add show
     * @param cineplexName The cineplex name
     * @param screenName The screen name
     * @param movieName The movie name
     * @param showDate The show date
     * @param showTime The show time
     * @return numeric value (0:successfully add show,1:cineplex is null,2: 
     * screen is null,3:movie is null,4:show already exists)
     */
    public int addShow(String cineplexName, String screenName, String movieName, String showDate, String showTime) {

        CineplexEY cineplex = this.cineplexMgr.getCineplexByName(cineplexName);
        if (cineplex == null) {
            return 1;
        }

        ScreenEY screen = this.screenMgr.getCineplexScreenByName(cineplex, screenName);
        if (screen == null) {
            return 2;
        }

        MovieEY movie = this.movieMgr.getMovieByName(movieName);
        if (movie == null) {
            return 3;
        }

        // Check if show already exits
        boolean showAlreadyExits = false;
        for (ShowEY show : masterShows) {
            if (show.getScreenID().equals(screen.getScreenID()) &&
                    show.getShowDate().equals(showDate) &&
                    show.getShowTime().equals(showTime)) {
                showAlreadyExits = true;
                break;
            }
        }

        if (showAlreadyExits) {
            return 4;

        }

        ShowEY show = new ShowEY("", movie.getMovieID(), cineplex.getCineplexID(), screen.getScreenID(), showDate,
                showTime, (screen.getNumberOfRows() * screen.getSeatsPerRow()), screen.getNumberOfRows(),
                screen.getSeatsPerRow(), movieMgr, screenMgr);
        masterShows.add(show);
        return 0;

    }

    /**
     * Method to update show with other show time or movie name
     * @param cineplexName The cineplex name
     * @param screenName The screen name
     * @param movieName The movie name
     * @param showDate The show date
     * @param showTime The show time
     * @param newShowTime The new show time
     * @param newMovieName The new movie name
     * @return numeric value (0: successfully updated show,1:all parameter are empty,2:cineplex is empty/null,
     * 3:screen name is empty/null,4:movie name is empty/null,5: matching show is null,
     * 6:new movie name & new showtime is empty,7:new movie is null)
     */
    public int updateShow(String cineplexName, String screenName, String movieName, String showDate, String showTime, String newShowTime, String newMovieName) {
  
        if (cineplexName.isEmpty() && screenName.isEmpty() && movieName.isEmpty() && showDate.isEmpty()) {
            return 1;
        }

        CineplexEY cineplex = this.cineplexMgr.getCineplexByName(cineplexName);
        if ((!cineplexName.isEmpty()) && cineplex == null) {
            return 2 ;
        }

        ScreenEY screen = this.screenMgr.getCineplexScreenByName(cineplex, screenName);
        if ((!screenName.isEmpty()) && screen == null) {
            return 3;
        }

        MovieEY movie = this.movieMgr.getMovieByName(movieName);
        if ((!movieName.isEmpty()) && movie == null) {
            return 4;
        }

        ShowEY matchingShow = null;
        for (ShowEY show : masterShows) {
            if (show.getScreenID().equals(screen.getScreenID()) &&
                    show.getShowDate().equals(showDate) &&
                    show.getShowTime().equals(showTime)) {
                matchingShow = show;
                break;
            }
        }

        if (matchingShow == null) {
            return 5;
        }

 
        if (newMovieName.isEmpty() && newShowTime.isEmpty()) {
            return 6;
        }

        if (newMovieName != null) {
            MovieEY newMovie = this.movieMgr.getMovieByName(newMovieName);
            if (newMovie == null) {
                return 7;
            }
            matchingShow.setMovieID(newMovie.getMovieID());
        }

        if (!newShowTime.isEmpty()) {
            matchingShow.changeShowTime(newShowTime);
        }

        return 0;
    }

    /**
     * Method to remove show
     * @param cineplexName The cineplex name
     * @param screenName The screen name
     * @param movieName The movie name
     * @param showDate The show date
     * @param showTime The show time
     * @return numeric value:0: successfully remove show
     * 1: all parameter empty
     * 2: cineplex name and cineplex object is null
     * 3: screen name is empty screen object is null
     * 4: movie name is empty and movie object is null
     * 5: matching show is null
     * 
     */
    public int removeShow(String cineplexName, String screenName, String movieName, String showDate, String showTime) {


        if (cineplexName.isEmpty() && screenName.isEmpty() && movieName.isEmpty() && showDate.isEmpty()) {
            return 1;
        }

        CineplexEY cineplex = this.cineplexMgr.getCineplexByName(cineplexName);
        if ((!cineplexName.isEmpty()) && cineplex == null) {
            return 2;
        }

        ScreenEY screen = this.screenMgr.getCineplexScreenByName(cineplex, screenName);
        if ((!screenName.isEmpty()) && screen == null) {
            return 3;
        }

        MovieEY movie = this.movieMgr.getMovieByName(movieName);
        if ((!movieName.isEmpty()) && movie == null) {
            return 4;
        }

        ShowEY matchingShow = null;
        for (ShowEY show : masterShows) {
            if (show.getScreenID().equals(screen.getScreenID()) &&
                    show.getShowDate().equals(showDate) &&
                    show.getShowTime().equals(showTime)) {
                matchingShow = show;
                break;
            }
        }

        if (matchingShow == null) {
            return 5;
        }

        masterShows.remove(matchingShow);

        return 0;
    }

    /**
     * Method to list show details
     * @param cineplexName The cineplex name
     * @param screenName The screen name
     * @param movieName The movie name
     * @param showDate The show date
     * @param showTime The show time
     * @return prints screen name, movie name, show date and time, empty seats
     */
    public ArrayList<String> listShows(String cineplexName, String screenName, String movieName, String showDate, String showTime) 
    {
   
        ArrayList<ShowEY> matchingShows = new ArrayList<ShowEY>();
        for (ShowEY show : masterShows) {
            CineplexEY cineplex = this.cineplexMgr.getCineplexByName(cineplexName);
    
            ScreenEY screen = this.screenMgr.getCineplexScreenByName(cineplex, screenName);
    
            MovieEY movie = this.movieMgr.getMovieByName(movieName);
    

            if (((cineplex == null) || show.getCineplexID().equals(cineplex.getCineplexID())) &&
                    ((screen == null) || show.getScreenID().equals(screen.getScreenID())) &&
                    ((movie == null) || show.getMovieID().equals(movie.getMovieID())) &&
                    ((showDate.isEmpty()) || showDate.equals(show.getShowDate())) &&
                    ((showTime.isEmpty()) || showTime.equals(show.getShowTime()))) {
                matchingShows.add(show);
            }
        }
        ArrayList<String> printLines = new ArrayList<String>();
        printLines.add("\n" + "The Show list :" + "\n");
        for (ShowEY show : matchingShows) {
          ScreenEY screen = screenMgr.getScreenByID(show.getScreenID());
          MovieEY movie = movieMgr.getMovieByID(show.getMovieID());
          printLines.add(
            "\n" +
            String.format("| %-20s", screen.getScreenName()) +
            String.format("| %-40s",movie.getName()) +

            String.format("| %-12s",show.getShowDate()) +
            String.format("| %-10s",show.getShowTime()) +
            " : Available Seats : " +
            String.format("| %-3s |",show.getEmptySeats()) 
          );
        }
        return printLines;
      }

    /**
     * Method to read show details from text file
     * @throws IOException If there's read error
     */
    public void primeShow() throws IOException {
        String showSEPARATOR = "|";
        String ShowSeatSEPARATOR = "~";
        String filename = this.getCentralManager().getDataFolder().concat("Shows.txt");
        ArrayList stringArray = null;
        try {
            stringArray = (ArrayList) ioManager.read(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Priming of Show objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, showSEPARATOR); // pass in the string to the string tokenizer
                                                                           // using delimiter ","
            String showID = star.nextToken().trim(); // first token
            String showDate = star.nextToken().trim(); // second token
            String showTime = star.nextToken().trim(); // third token
            String movieID = star.nextToken().trim();
            String cineplexID = star.nextToken().trim(); // fifth token
            String screenID = star.nextToken().trim(); // fifth token
            // Movie movie=null;
            // for (Movie movieIterator : masterMovies) {
            // if (movieIterator.getMovieID().equals(movieID)) {
            // movie = movieIterator;
            // break;
            // }
            // }
            // String screenID = star.nextToken().trim(); // fifth token
            // Screen screen=null;
            // for (Screen screenIterator : masterScreens) {
            // if (screenIterator.getScreenID().equals(screenID)) {
            // screen = screenIterator;
            // break;
            // }
            // }
            int numberOfRows = Integer.parseInt(star.nextToken().trim());
            int seatsPerRow = Integer.parseInt(star.nextToken().trim());
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<ShowSeatEY> showSeats = new ArrayList<ShowSeatEY>();
            String ShowSeatsString, ShowSeatID, ShowSeatRow, ShowSeatType, occupiedString;
            boolean isOccupied;
            ShowSeatsString = star.nextToken().trim();
            StringTokenizer ShowSeatsToken = new StringTokenizer(ShowSeatsString, ShowSeatSEPARATOR);

            while (ShowSeatsToken.hasMoreTokens()) {
                ShowSeatID = ShowSeatsToken.nextToken().trim();
                ShowSeatRow = ShowSeatsToken.nextToken().trim();
                int ShowSeatNumber = Integer.parseInt(ShowSeatsToken.nextToken().trim());
                ShowSeatType = ShowSeatsToken.nextToken().trim();
                occupiedString = ShowSeatsToken.nextToken().trim();
                if (occupiedString.equals("Y")) {
                    isOccupied = true;
                } else {
                    isOccupied = false;
                }
                ShowSeatEY showSeat = new ShowSeatEY(ShowSeatID, ShowSeatRow, ShowSeatNumber, ShowSeatType, isOccupied);
                showSeats.add(showSeat);

            }
            ShowEY show = new ShowEY(showID, movieID, cineplexID, screenID, showDate, showTime, emptySeats, numberOfRows,
                    seatsPerRow, showSeats, this.movieMgr, this.screenMgr);
            this.masterShows.add(show);
        }

    }

    /**
     * Method to write show details to text file
     * @throws IOException If there's write error
     */
    public void writeShow() throws IOException {
        String showSEPARATOR = " | ";
        String ShowSeatSEPARATOR = " ~ ";
        String filename = this.getCentralManager().getDataFolder().concat("Shows.txt");
        List alw = new ArrayList();
        ArrayList<ShowSeatEY> ShowSeats;
        ShowEY show;
        for (int i = 0; i < this.masterShows.size(); i++) {
            show = this.masterShows.get(i);
            StringBuilder st = new StringBuilder();
            st.append(show.getShowID().trim());
            st.append(showSEPARATOR);
            st.append(show.getShowDate().trim());
            st.append(showSEPARATOR);
            st.append(show.getShowTime().trim());
            st.append(showSEPARATOR);
            st.append(show.getMovieID().trim());
            st.append(showSEPARATOR);
            st.append(show.getCineplexID().trim());
            st.append(showSEPARATOR);
            st.append(show.getScreenID().trim());
            st.append(showSEPARATOR);
            st.append(show.getNumberOfRows());
            st.append(showSEPARATOR);
            st.append(show.getSeatsPerRow());
            st.append(showSEPARATOR);
            st.append(show.getEmptySeats());
            st.append(showSEPARATOR);

            ShowSeats = show.getSeatLayout();
            for (int j = 0; j < ShowSeats.size(); j++) {
                ShowSeatEY ShowSeat = ShowSeats.get(j);
                st.append(ShowSeat.getSeatID().trim());
                st.append(ShowSeatSEPARATOR);
                st.append(ShowSeat.getSeatRow().trim());
                st.append(ShowSeatSEPARATOR);
                st.append(ShowSeat.getSeatNumber());
                st.append(ShowSeatSEPARATOR);
                st.append(ShowSeat.getSeatType().trim());
                st.append(ShowSeatSEPARATOR);
                if (ShowSeat.isOccupied()) {
                    st.append("Y");
                } else {
                    st.append("N");
                }
                st.append(ShowSeatSEPARATOR);
            }
            alw.add(st.toString());

        }
        ioManager.write(filename, alw);
        ;

    }

	/**
	 * Method to validate information for show boundary
	 * @param cineplexName The cineplex name
     * @param screenName The screen name
     * @param movieName The movie name
     * @param showDate The show date
     * @param showTime The show time
	 * @return numeric value (0:credible information,1:no values entered,2:cineplex not found,3:screen not found,4:movie not found)
	 */
	public int validateList(String cineplexName, String screenName, String movieName, String showDate,
			String showTime) {
   if (cineplexName.isEmpty() && screenName.isEmpty() && movieName.isEmpty() && showDate.isEmpty()) {
            return 1;
        }

        CineplexEY cineplex = this.cineplexMgr.getCineplexByName(cineplexName);
        if ((!cineplexName.isEmpty()) && cineplex == null) {
            return 2;
        }

        ScreenEY screen = this.screenMgr.getCineplexScreenByName(cineplex, screenName);
        if ((!screenName.isEmpty()) && screen == null) {
            return 3;
        }

        MovieEY movie = this.movieMgr.getMovieByName(movieName);
        if ((!movieName.isEmpty()) && movie == null) {
            return 4;
        }    
                  
		return 0;
	}

    /**
     * Method to get showID from show date
     * @param cineplexID The cineplexID
     * @param movieID The movieID
     * @param viewShow The user's choice of show
     * @return The show date as the showID
     */
    public String getShowIDFromShowDateIDX(String cineplexID, String movieID, int viewShow) {
        
        int count = 0;
        for (ShowEY show : masterShows)
        {   
            if (show.getCineplexID().equals(cineplexID) &&
                show.getMovieID().equals(movieID) ){
                count++;
                if (count == viewShow ) 
                { 
                    return show.getShowDate();
                }
            }

        }
        return "";
    }

    /**
     * Method to get show date for a movie at A cineplex
     * @param cineplexID The cineplexID
     * @param movieID The movieID
     * @return The list of show date for a movie at a cineplex
     */
    public ArrayList<String> getShowDateForMovieAtCineplex(String cineplexID, String movieID) {
        ArrayList<String>  lines = new ArrayList<String> ();
        int count = 0;
        for (ShowEY show : masterShows)
        {
            if (show.getCineplexID().equals(cineplexID) &&
                show.getMovieID().equals(movieID) )
            {
                count++;
                lines.add(count + ": " + show.getShowDate());

            }
        }
        return lines;
    }

    /**
     * Method to get showID from show time index
     * @param cineplexID The cineplexID
     * @param movieID The movieID
     * @param showDate The show date
     * @param viewShow The user choice of show
     * @return The arraylist containing show time,screenID,showID and screenID
     */
    public ArrayList<String> getShowIDFromShowTimeIDX(String cineplexID, String movieID, String showDate, int viewShow) {
        int count = 0;
        ArrayList<String>  showDateScreen = new ArrayList<String> ();

        for (ShowEY show : masterShows)
        {   
            if (show.getCineplexID().equals(cineplexID) &&
                show.getMovieID().equals(movieID) &&
                show.getShowDate().equals(showDate))
            {
                count++;
                if (count == viewShow ) 
                { 
                    showDateScreen.add(show.getShowTime());
                    showDateScreen.add(show.getScreenID());
                    showDateScreen.add(show.getShowID());
                    showDateScreen.add(screenMgr.getScreenNameByID(show.getScreenID()));
                    return showDateScreen;                
                }
            }
        }
            return null;
        }

    /**
     * Method to get show time for a movie at a cineplex
     * @param cineplexID The cineplex ID
     * @param movieID The movie ID
     * @param showDate The show date
     * @return The list of movie show time and screen names in a cineplex 
     */
    public ArrayList<String>  getShowTimeForMovieAtCineplex(String cineplexID, String movieID, String showDate) {
        ArrayList<String>  lines = new ArrayList<String> ();
        int count = 0;
        for (ShowEY show : masterShows)
        {
            if (show.getCineplexID().equals(cineplexID) &&
                show.getMovieID().equals(movieID)  &&
                show.getShowDate().equals(showDate))
            {
                count++;
                lines.add(count + ": " + show.getShowTime() + " on screen " + screenMgr.getScreenNameByID(show.getScreenID()));

            }
        }
        return lines;    
    }

    /**
     * Method to showSeatLayout
     * @param showID The showID
     * @return The seat layout for a show
     */
    public ArrayList<String> ShowSeatLayout(String showID) {
        for (ShowEY show : masterShows)
        {
            if (show.getShowID().equals(showID)){
                return show.ShowSeatLayout();

            }
             
        }
        return null;
    }

    /**
     * Method set seat to occupied
     * @param showID The showID
     * @param seatIDs The seatIDs
     */
    public void setSeatOccupied(String showID, ArrayList<String> seatIDs) {
        ShowEY  show = this.getShowbyID(showID);
        ArrayList<ShowSeatEY> showSeats = show.getShowSeats();
        for (String seatid : seatIDs){
            for (ShowSeatEY showSeat : showSeats){
                if (showSeat.getSeatID().equals(seatid)){
                    showSeat.setOccupied(true);
                }
            }
        }

    }

    /**
     * Method to get show by showID
     * @param showID The showID
     * @return The show object
     */
    private ShowEY getShowbyID(String showID) {
        for (ShowEY show : masterShows){
            if (show.getShowID().equals(showID)){
                return show;
            }
        }
        
        return null;

    }
	/**
	* Method to get shows of a particular movie
	* @param movie The movie whose shows are to be seen
	* @return The list of shows
	*/
    public ArrayList<String> getShowsByMovie(MovieEY movie) {


        ArrayList<String> lines = new ArrayList<String>();
        lines.add("\nFollowing are the Shows for the Movie : " + movie.getName() + "\n");
        for (ShowEY show : this.masterShows){
            if (show.getMovieID().equals(movie.getMovieID())){
                String screenName = this.screenMgr.getScreenNameByID(show.getScreenID());
                String ShowDate = show.getShowDate();
                String ShowTime = show.getShowTime();

                lines.add(
                    String.format("| %-20s | ",screenName) +
                    ShowDate + "  @  " + ShowTime + " | "
                );
            }
        }
        return lines;
    }

 
}
