import java.util.ArrayList;

public class Show {

	private String showDate;
	private String  showTime;
	private String movieID;
	private String screenID;
	private int numberOfRows;
	private int seatsPerRow;
	private int emptySeats;
	private ArrayList<ShowSeat>showSeats=new ArrayList<ShowSeat>();


	/**
	 * 
	 * @param showDate
	 * @param showTime
	 */
	public Show(String showDate, String showTime, String movieID,Screen screen) {
		// TODO - implement Show.Show
		this.showDate=showDate;
		this.showTime=showTime;
		this.movieID=movieID;
		this.screenID=screen.getScreenID();
		emptySeats=screen.getNumberOfSeats();
		numberOfRows=screen.getNumberOfRows();
		seatsPerRow=screen.getSeatsPerRow();
		ArrayList<Seat> seats= screen.getSeatLayout(); 
		for (int i=0;i<seats.size();i++){
			Seat seat=seats.get(i);
			ShowSeat showSeat= new ShowSeat(seat.getSeatID(), seat.getSeatRow(), seat.getSeatNumber(), seat.getSeatType());
			showSeats.add(showSeat);
		}

	}

	

	/**
	 * 
	 * @param showDate
	 * @param showTime
	 * @param screen
	 */

	/**
	 * 
	 * @param movie
	 */


	public int getEmptySeats() {
		return this.emptySeats;
	}

	/**
	 * 
	 * @param emptySeats
	 */
	public void setEmptySeats(int emptySeats) {
		this.emptySeats = emptySeats;
	}

	public void ShowSeatLayout() {
		// TODO - implement Show.ShowAvailableSeats
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
class Test{
	public static void main(String[] args){
		Screen screen=new Screen("abcdl", ScreenClass.PLATINUM_MOVIE_SUITES, 5, 10);
		Show show=new Show("03-11-22","9:00 pm","abcd",screen);
		show.ShowSeatLayout();

	}
}