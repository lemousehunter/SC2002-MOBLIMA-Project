import java.util.ArrayList;
import java.util.UUID;


public class Show {
	private String showID;
	private String showName;
	private String showDate;
	private String showTime;
	private Screen screen;
	private Movie movie;
	private String movieID;
	private ArrayList<ShowSeat>showSeats=new ArrayList<ShowSeat>();

	/**
	 *
	 * @param showDate
	 * @param showTime
	 */
	public Show(String movieID, String showDate, String showTime, String screenID, int emptySeats, int numberOfRows, int seatsPerRow) {
		MovieManager movieMgr = new MovieManager();

		// TODO - implement Show.Show
		this.showID = UUID.randomUUID().toString();
		this.movieID = movieID;
		this.movie = movieMgr.getMovieByID(movieID);
		this.showName = this.movie.getName();
		this.showDate = showDate;
		this.showTime = showTime;
		screenManager screenMgr = new screenManager();
		this.screen = screenMgr.getScreenByID(screenID);
	}

	public String getShowID(){
		return this.showID;
	}

	public String getMovieID() {
		return this.movieID;
	}

	public String getShowName() {
		return showName;
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

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public void ShowSeatLayout() {
		// TODO - implement Show.ShowAvailableSeats
		boolean aisleSpace=true;
		String printRowString="      ";

		for (int j=1;j<=screen.getSeatsPerRow();j++){
			if (j<10){
				printRowString=printRowString.concat( "  "+Integer.toString(j)+"  ");
			}
			else{
				printRowString=printRowString.concat( " "+Integer.toString(j)+"  ");
			}
			if(j==screen.getSeatsPerRow()/2){
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