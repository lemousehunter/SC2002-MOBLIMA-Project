import java.util.ArrayList;
import java.util.UUID;
/**
 * A ShowEY Object
 * 
 * <p>A <code>ShowEY</code> object used 
 * to store all parameters about shows
 * </p>
 * 
 */

public class ShowEY {
	private String showID;
	private String showDate;
	private String showTime;
	private String cineplexID;


	private String screenID;
	private String movieID;

	private ScreenEY screen;
	private MovieEY movie;
	private int emptySeats;
	private int numberOfRows;
	private int seatsPerRow;
	private ArrayList<ShowSeatEY> showSeats;

	private MovieManager movieMgr;
	private ScreenManager screenMgr;

	
	/**
	 * Constructor for ShowEY object
	 * @param showID The showID
	 * @param movieID The movieID
	 * @param cineplexId The cineplexID
	 * @param screenID The screenID
	 * @param showDate The show date
	 * @param showTime The show time
	 * @param emptySeats The number of empty seats
	 * @param numberOfRows The number of rows
	 * @param seatsPerRow The number of seats per row
	 * @param movieMgr A movie manager object
	 * @param screenMgr A screen manager object
	 */
	public ShowEY(String showID, String movieID, String cineplexId, String screenID, String showDate, String showTime, int emptySeats, int numberOfRows, int seatsPerRow, MovieManager movieMgr, ScreenManager screenMgr) {
		// TODO - implement Show.Show
		// Managers
		this.movieMgr = movieMgr;
		this.screenMgr = screenMgr;

		// Attributes
		if (showID.isEmpty()) {
			this.showID = UUID.randomUUID().toString();
		}
		else {
			this.showID = showID;
		}
		this.showID = showID;
		this.movieID = movieID;
		this.showDate = showDate;
		this.showTime = showTime;
		this.cineplexID = cineplexId;
		this.screenID = screenID;
		this.emptySeats = emptySeats;
		this.numberOfRows = numberOfRows;
		this.seatsPerRow = seatsPerRow;
		this.movie = this.movieMgr.getMovieByID(movieID);
		this.screenID=screenID;
		this.screen = this.screenMgr.getScreenByID(screenID);
		this.showSeats = new ArrayList<ShowSeatEY>();
		ArrayList<SeatEY> seats= screen.getSeatLayout();
		for (int i=0;i<seats.size();i++){
			SeatEY seat=seats.get(i);
			ShowSeatEY showSeat= new ShowSeatEY(seat.getSeatID(), seat.getSeatRow(), seat.getSeatNumber(), seat.getSeatType());
			showSeats.add(showSeat);
		}

	}

	/**
	 * Method to get the showID
	 * @return The showID
	 */
	public String getShowID(){
		return this.showID;
	}

	/**
	 * Method to get the movieID
	 * @return The movieID
	 */
	public String getMovieID() {
		return this.movieID;
	}

	/**
	 * Method to get the show date
	 * @return The show date
	 */
	public String getShowDate() {
		return this.showDate;
	}

	/**
	 * Method to get the show time
	 * @return The show time
	 */
	public String getShowTime() {
		return this.showTime;
	}
	
		
	/**
	 * Method to get the screenID
	 * @return The screen ID
	 */
	public String getScreenID() {
		return screenID;
	}
	/**
	 * Method to get the screen object
	 * @return The screenEY object
	 */
	public ScreenEY getScreen() {
		return screen;
	}
	/**
	 * Method to get the MovieEY object
	 * @return The MovieEY object
	 */
	public MovieEY getMovie() {
		return movie;
	}

	/**
	 * Method to get number of rows
	 * @return The number of rows
	 */
	public int getNumberOfRows() {
		return this.numberOfRows;
	}
	/**
	 * Method to get the number of seats per row
	 * @return The number of seats per row
	 */
	public int getSeatsPerRow() {
		return this.seatsPerRow;
	}
	/**
	 * Method to get the cineplexID
	 * @return The cineplexID
	 */
	public String getCineplexID() {
		return cineplexID;
	}

	/**
	 * Method to set the movieID
	 * @param movieID The movieID
	 */
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	/**
	 * Method to get number of empty seats
	 * @return The number of empty seats
	 */
	public int getEmptySeats() {
		return this.emptySeats;
	}

	/**
	 * Method to get the seat layout
	 * @return The layout of the seats in arraylist
	 */
	public ArrayList<ShowSeatEY> getSeatLayout() {
		return showSeats;
	}

	/**
	 * Method to change the show time
	 * @param showTime The show time to change into
	 */
	public void changeShowTime(String showTime) {
		this.showTime = showTime;
	}

	/**
	 * Method to print the seat layout in the cineplex
	 */
	public void ShowSeatLayout() {
		boolean aisleSpace=true;
		String printRowString="      ";

		for (int j=1;j<=seatsPerRow;j++){
			if (j<10){
				printRowString=printRowString.concat( "  "+Integer.toString(j)+"  ");
			}
			else{
				printRowString=printRowString.concat( " "+Integer.toString(j)+"  ");
			}
			if(j==seatsPerRow/2){
				printRowString=printRowString.concat("          ");
			}
		}
		System.out.print("                           SCREEN FACING HERE          "+"\n");
		System.out.print("----------------------------------------------------------------------"+"\n");
		System.out.println(printRowString);


		printRowString="";
		for (int i=0;i<showSeats.size();i++){
			ShowSeatEY showSeat=showSeats.get(i);
			if (showSeat.getSeatNumber()==1){
				System.out.println(printRowString+"\n");
				String RowString=showSeat.getSeatRow();
				printRowString="";
				if (RowString.length()==1){
					printRowString=printRowString.concat("  "+RowString+"   ");
	
				}
				else{
					printRowString=printRowString.concat("  "+RowString+"  ");
				}
				aisleSpace=true;
			}
			if(showSeat.isOccupied()){
				printRowString=printRowString.concat("  X   ");

			}
			else{
				printRowString=printRowString.concat("  _  ");

			}
			if ( aisleSpace && showSeat.getSeatType()=="AISLE"){
				printRowString=printRowString.concat("          ");
				aisleSpace=false;
			}
			if(i==showSeats.size()){
				System.out.println(printRowString+"\n\n");

			}

		}
	}


}
