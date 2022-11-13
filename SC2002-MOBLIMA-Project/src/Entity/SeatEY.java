package Entity;

	/**
	 * A Seat object 
	 * 
	 * <p>
	 * A <code>SeatEY</code> object used to store
	 * all parameters about seats
	 * </p>
	 */
public class SeatEY {
	private String seatID;
	private String seatRow;
	private int seatNumber;
	private String seatType;
	/**
	 * Constructor for SeatEY object (require 3 parameter)
	 * @param seatRow The seat row
	 * @param seatNumber The seat number
	 * @param seatType  The seat type
	 */
	public SeatEY(String seatRow, int seatNumber, String seatType) {
		this.seatID=seatRow;
		this.seatID=this.seatID.concat(Integer.toString(seatNumber));
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
	}
	/**
	 * Constructor for SeatEY object (require 4 parameter)
	 * @param seatID The seatID
	 * @param seatRow The seat row
	 * @param seatNumber The seat number
	 * @param seatType The seat type
	 */
	public SeatEY(String seatID, String seatRow, int seatNumber, String seatType) {
		this.seatID=seatID;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
	}
	/**
	 * Method to get seatID
	 * @return The seatID
	 */
	public String getSeatID() {
		return seatID;
	}
	/**
	 * Method to get seat row
	 * @return The seat row
	 */
	public String getSeatRow() {
		return seatRow;
	}

	/**
	 * Method to set seat row
	 * @param seatRow The seat row
	 */
	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}

	/**
	 * Method to get seat number
	 * @return The seat number
	 */
	public int getSeatNumber() {
		return seatNumber;
	}

	/**
	 * Method to set seat number
	 * @param seatNumber The seat Number
	 */
	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * Method to get seat type
	 * @return the seat type
	 */
	public String getSeatType() {
		return seatType;
	}

	/**Method to set seat type
	 * @param seatType The seat type
	 */
	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	

}