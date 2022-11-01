public class Screen {

	private String screenName;
	private int numberOfSeats;
	private ScreenClass screenClass;
	private int numberOfRows;
	private int seatsPerRow;

	/**
	 *
	 * @param screenName
	 * @param screenClass
	 * @param numberOfRows
	 * @param seatsPerRow
	 */
	public Screen(String screenName, ScreenClass screenClass, int numberOfRows, int seatsPerRow) {

		this.screenName=screenName;
		this.screenClass=screenClass;
		this.numberOfRows=numberOfRows;
		this.seatsPerRow=seatsPerRow;
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

}