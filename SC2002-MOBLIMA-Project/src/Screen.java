import java.util.ArrayList;
import java.util.UUID;

public class Screen {

	
	private String screenID;
	private String screenName;
	private int numberOfSeats;
	private ScreenClass screenClass;
	private int numberOfRows;
	private int seatsPerRow;
	private ArrayList<Seat> seatLayout= new ArrayList<Seat>();

	/**
	 *
	 * @param screenName
	 * @param screenClass
	 * @param numberOfRows
	 * @param seatsPerRow
	 */
	public Screen(String screenID, String screenName, String screenClass, int numberOfRows, int seatsPerRow) {
		if (screenID.isEmpty()) {
			this.screenID = UUID.randomUUID().toString();
		}
		else {
			this.screenID = screenID;
		}
		this.screenName=screenName;
		this.screenClass= ScreenClass.valueOf(screenClass);
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;
		this.numberOfSeats=numberOfRows*seatsPerRow;
		String seatID;
		String seatRow;
		int seatNumber;
		String seatType;
		int leftAisle= seatsPerRow/2;
		int rightAisle=leftAisle+1;
		for(int i=1;i<=numberOfRows;i++){
			for(int j=1;j<=seatsPerRow;j++){
				seatRow=getRowByNumber(i);
				seatNumber=j;
				seatID=seatRow;
				seatID=seatID.concat(Integer.toString(seatNumber));
				if(j==leftAisle || j==rightAisle){
					seatType="AISLE";
				}
				else{
					seatType="REGULAR";
				}
				Seat seat=new Seat(seatID, seatRow, seatNumber, seatType);
				this.seatLayout.add(seat);
			}
			
		}

	}
	public Screen(String screenID, String screenName, String screenClass, int numberOfRows, int seatsPerRow,ArrayList<Seat> seatLayout) {
		this.screenID = screenID;
		this.screenName=screenName;
		this.screenClass= ScreenClass.valueOf(screenClass);
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;
		this.numberOfSeats=numberOfRows*seatsPerRow;
		this.seatLayout=seatLayout;
	}

	public ScreenClass getScreenType() {
		return this.screenClass;
	}

	public Boolean getBooleanScreenType() {
		ScreenClass sc = this.getScreenType();
		if (this.screenClass.equals(sc))
		{
			return true;
		}
		else return false;
	}
	public String getScreenID() {
		return screenID;
	}

	
	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public ScreenClass getScreenClass() {
		return screenClass;
	}

	public void setScreenClass(ScreenClass screenClass) {
		this.screenClass = screenClass;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}

	public ArrayList<Seat> getSeatLayout() {
		return seatLayout;
	}

	private String getRowByNumber(int rowNumber){
		int quotient=rowNumber/26;
		int remainder=rowNumber%26;
		String rowString="";
		if (quotient>0){
			rowString=rowString.concat(Character.toString(mapNumberToChar(quotient)));
		}
		if (remainder>0){
			rowString=rowString.concat(Character.toString(mapNumberToChar(remainder)));
		}
		return rowString;

	}
	private char mapNumberToChar(int number){
		return (char) (64+number);
	}

	public String viewDetails() {
		return  String.format("| %-20s",this.screenName) + 
				" " + 
				String.format("| %-22s |", this.screenClass.toString()) + 
				" #Rows = " + Integer.toString(this.numberOfRows) + " ; #Seats per Row = " + Integer.toString(this.seatsPerRow) ; 
	}
	
	
	
}