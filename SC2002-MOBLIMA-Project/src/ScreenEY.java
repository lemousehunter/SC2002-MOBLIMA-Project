import java.util.ArrayList;
import java.util.UUID;

public class ScreenEY {

	
	private String screenID;
	private String screenName;
	private int numberOfSeats;
	private ScreenClassEN screenClass;
	private int numberOfRows;
	private int seatsPerRow;
	private ArrayList<SeatEY> seatLayout= new ArrayList<SeatEY>();

	/**
	 *
	 * @param screenName
	 * @param screenClass
	 * @param numberOfRows
	 * @param seatsPerRow
	 */
	public ScreenEY(String screenID, String screenName, String screenClass, int numberOfRows, int seatsPerRow) {
		if (screenID.isEmpty()) {
			this.screenID = UUID.randomUUID().toString();
		}
		else {
			this.screenID = screenID;
		}
		this.screenName=screenName;
		this.screenClass= ScreenClassEN.valueOf(screenClass);
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
				SeatEY seat=new SeatEY(seatID, seatRow, seatNumber, seatType);
				this.seatLayout.add(seat);
			}
			
		}

	}
	public ScreenEY(String screenID, String screenName, String screenClass, int numberOfRows, int seatsPerRow, ArrayList<SeatEY> seatLayout) {
		this.screenID = screenID;
		this.screenName=screenName;
		this.screenClass= ScreenClassEN.valueOf(screenClass);
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;
		this.numberOfSeats=numberOfRows*seatsPerRow;
		this.seatLayout=seatLayout;
	}

	public ScreenClassEN getScreenType() {
		return this.screenClass;
	}

	public Boolean getBooleanScreenType() {
		ScreenClassEN sc = this.getScreenType();
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

	public ScreenClassEN getScreenClass() {
		return screenClass;
	}

	public void setScreenClass(ScreenClassEN screenClass) {
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

	public ArrayList<SeatEY> getSeatLayout() {
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