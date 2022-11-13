package Entity;

import java.util.ArrayList;
import java.util.UUID;
import Enum.ScreenClassEN;

/**
 * A ScreenEY object 
 * 
 * <p>
 * A <code>ScreenEY</code> object used to store all 
 * parameters about the screen
 * </p>
 */
public class ScreenEY {

	
	private String screenID;
	private String screenName;
	private int numberOfSeats;
	private ScreenClassEN screenClass;
	private int numberOfRows;
	private int seatsPerRow;
	private ArrayList<SeatEY> seatLayout= new ArrayList<SeatEY>();

	
	/**
	 * Constructor for ScreenEY object (require 5 parameter)
	 * @param screenID The screenID
	 * @param screenName The screen Name
	 * @param screenClass The screen class
	 * @param numberOfRows The number of rows
	 * @param seatsPerRow The number of seats per row
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
	/**
	 * Constructor for ScreenEY object (require 6 parameter)
	 * @param screenID The screenID
	 * @param screenName The screen Name
	 * @param screenClass The screen class
	 * @param numberOfRows The number of rows
	 * @param seatsPerRow The number of seats per row
	 * @param seatLayout A list of SeatEY objects as screen layout
	 */
	public ScreenEY(String screenID, String screenName, String screenClass, int numberOfRows, int seatsPerRow, ArrayList<SeatEY> seatLayout) {
		this.screenID = screenID;
		this.screenName=screenName;
		this.screenClass= ScreenClassEN.valueOf(screenClass);
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;
		this.numberOfSeats=numberOfRows*seatsPerRow;
		this.seatLayout=seatLayout;
	}

	/**
	 * Method to get screen type
	 * @return The enum value of screen type (PLATINUM_MOVIE_SUITES,REGULAR_SCREEN)
	 */
	public ScreenClassEN getScreenType() {
		return this.screenClass;
	}

	/**
	 * Method to check if its the same screen type
	 * @return true if same screen type, false if not the same screen type
	 */
	public Boolean getBooleanScreenType() {
		ScreenClassEN sc = this.getScreenType();
		return this.screenClass.equals(sc);
	}
	/**
	 * Method to get screenID
	 * @return The screenID
	 */
	public String getScreenID() {
		return screenID;
	}

	
	/**
	 * Method to get screen name
	 * @return The screen name
	 */
	public String getScreenName() {
		return screenName;
	}

	/**
	 * Method to set screen name
	 * @param screenName The screen name
	 */
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	/**
	 * Method to get the number of seats 
	 * @return The number of seats
	 */
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	/**
	 * Method to set the number of seats
	 * @param numberOfSeats The number of seats
	 */
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	/**
	 * Method to get screen class
	 * @return The screen class enum value (PLATINUM_MOVIE_SUITES,REGULAR_SCREEN)
	 */
	public ScreenClassEN getScreenClass() {
		return screenClass;
	}

	/**
	 * Method to set screen class
	 * @param screenClass The screen class enum (PLATINUM_MOVIE_SUITES,REGULAR_SCREEN)
	 */
	public void setScreenClass(ScreenClassEN screenClass) {
		this.screenClass = screenClass;
	}

	/**
	 * Method to get number of rows
	 * @return The number of rows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * Method to set number of rows
	 * @param numberOfRows The number of rows
	 */
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	/**
	 * Method to get the number of seats per row
	 * @return the number of seats per row
	 */
	public int getSeatsPerRow() {
		return seatsPerRow;
	}

	/**
	 * Method to set the number of seats per row
	 * @param seatsPerRow The number of seats per row
	 */
	public void setSeatsPerRow(int seatsPerRow) {
		this.seatsPerRow = seatsPerRow;
	}

	/**
	 * Method to get seat layout
	 * @return The seat layout as list of SeatEY objects
	 */
	public ArrayList<SeatEY> getSeatLayout() {
		return seatLayout;
	}

	/**
	 * Methods to get row by row number
	 * @param rowNumber The row number
	 * @return The row
	 */
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
	/**
	 * Method to map number to character
	 * @param number The number to be mapped
	 * @return The character of the number
	 */
	private char mapNumberToChar(int number){
		return (char) (64+number);
	}

	/**
	 * Method to view Screen details
	 * @return The details of the screen
	 */
	public String viewDetails() {
		return  String.format("| %-20s",this.screenName) + 
				" " + 
				String.format("| %-22s |", this.screenClass.toString()) + 
				" #Rows = " + Integer.toString(this.numberOfRows) + " ; #Seats per Row = " + Integer.toString(this.seatsPerRow) ; 
	}
	
	
	
}