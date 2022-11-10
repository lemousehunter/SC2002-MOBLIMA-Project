import java.util.ArrayList;
import java.util.UUID;


public class ShowEY {
	private String showID;
	private String showDate;
	private String showTime;
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
	 *
	 * @param showDate
	 * @param showTime
	 */
	public ShowEY(String showID, String movieID, String screenID, String showDate, String showTime, int emptySeats, int numberOfRows, int seatsPerRow, MovieManager movieMgr, ScreenManager screenMgr) {
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
		this.screenID = screenID;
		this.emptySeats = emptySeats;
		this.numberOfRows = numberOfRows;
		this.seatsPerRow = seatsPerRow;
		this.movie = this.movieMgr.getMovieByID(movieID);
		this.screenID=screenID;
		this.screen = this.screenMgr.getScreenByID(screenID);
		ArrayList<SeatEY> seats= screen.getSeatLayout();
		for (int i=0;i<seats.size();i++){
			SeatEY seat=seats.get(i);
			ShowSeatEY showSeat= new ShowSeatEY(seat.getSeatID(), seat.getSeatRow(), seat.getSeatNumber(), seat.getSeatType());
			showSeats.add(showSeat);
		}

	}

	public String getShowID(){
		return this.showID;
	}

	public String getMovieID() {
		return this.movieID;
	}

	public String getShowDate() {
		return this.showDate;
	}

	public String getShowTime() {
		return this.showTime;
	}
	
	public String getScreenID() {
		return screenID;
	}
	public ScreenEY getScreen() {
		return screen;
	}
	public MovieEY getMovie() {
		return movie;
	}

	public int getNumberOfRows() {
		return this.numberOfRows;
	}
	public int getSeatsPerRow() {
		return this.seatsPerRow;
	}

	public int getEmptySeats() {
		return this.emptySeats;
	}

	public ArrayList<ShowSeatEY> getSeatLayout() {
		return showSeats;
	}

	public void changeShowTime(String showTime) {
		this.showTime = showTime;
	}

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
