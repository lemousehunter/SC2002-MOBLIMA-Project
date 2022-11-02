import java.util.UUID;

public class Screen {

	
	private String screenID;
	private String screenName;
	private int numberOfSeats;
	private ScreenClass screenClass;
	private int numberOfRows;
	private int seatsPerRow;
	private screenType screenType;

	/**
	 *
	 * @param screenName
	 * @param screenClass
	 * @param numberOfRows
	 * @param seatsPerRow
	 */
	public Screen(String screenName, ScreenClass screenClass, int numberOfRows, int seatsPerRow,screenType type) {
		
		this.screenID = UUID.randomUUID().toString();
		this.screenName=screenName;
		this.screenClass=screenClass;
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;
		this.screenType=type;
	}

	/**
	 *
	 * @param numberOfRows
	 * @param SeatsPerRow
	 */
	public void addScreenSeatLayout(int numberOfRows, int SeatsPerRow) {
		
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=SeatsPerRow;
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

	public screenType getScreenType() {
		return screenType;
	}

	public void setScreenType(screenType screenType) {
		this.screenType = screenType;
	}

	
	
	
}