public class SeatEY {
	private String seatID;
	private String seatRow;
	private int seatNumber;
	private String seatType;
	/**
	 * 
	 * @param seatRow
	 * @param seatNuber
	 * @param seatType
	 */
	public SeatEY(String seatRow, int seatNumber, String seatType) {
		this.seatID=seatRow;
		this.seatID=this.seatID.concat(Integer.toString(seatNumber));
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
	}
	/**
	 * 
	 * @param seatID
	 * @param seatRow
	 * @param seatNuber
	 * @param seatType
	 * 
	 */
	public SeatEY(String seatID, String seatRow, int seatNumber, String seatType) {
		this.seatID=seatID;
		this.seatRow = seatRow;
		this.seatNumber = seatNumber;
		this.seatType = seatType;
	}
	public String getSeatID() {
		return seatID;
	}
	public String getSeatRow() {
		return seatRow;
	}

	public void setSeatRow(String seatRow) {
		this.seatRow = seatRow;
	}

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public String getSeatType() {
		return seatType;
	}

	public void setSeatType(String seatType) {
		this.seatType = seatType;
	}

	

}