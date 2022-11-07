import java.util.ArrayList;
import java.util.UUID;


public class Show {
	private String showID;
	private String showDate;
	private String showTime;
	private String screenID;
	private String movieID;
	private Screen screen;
	private Movie movie;
	private int emptySeats;
	private int numberOfRows;
	private int seatsPerRow;
	private ArrayList<ShowSeat>showSeats=new ArrayList<ShowSeat>();

	/**
	 *
	 * @param showDate
	 * @param showTime
	 */
	public Show(String movieID,Movie movie,Screen screen, String showDate, String showTime, String screenID, int emptySeats, int numberOfRows, int seatsPerRow) {
	
		// TODO - implement Show.Show
		this.showID = UUID.randomUUID().toString();
		this.movieID = movieID;
		this.movie=movie;
		this.screen=screen;
		this.showDate = showDate;
		this.showTime = showTime;
		this.screenID =screenID;
		this.emptySeats=emptySeats;
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;


	}
	public Show(String showID, String movieID,Movie movie,Screen screen, String showDate, String showTime, String screenID, int emptySeats, int numberOfRows, int seatsPerRow) {
		// TODO - implement Show.Show
		this.showID=showID;
		this.movieID = movieID;
		this.movie=movie;
		this.screen=screen;
		this.showDate = showDate;
		this.showTime = showTime;
		this.screenID =screenID;
		this.emptySeats=emptySeats;
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;
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

	public void changeShowTime(String showTime) {
		this.showTime = showTime;
	}

	
	public String getScreenID() {
		return screenID;
	}
	public Screen getScreen() {
		return screen;
	}
	public Movie getMovie() {
		return movie;
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
			ShowSeat showSeat=showSeats.get(i);
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
