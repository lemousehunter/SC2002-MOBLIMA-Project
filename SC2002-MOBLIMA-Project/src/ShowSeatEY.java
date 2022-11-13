//package com.moblima.app;
    /**
	 * A Show seat object that is derived from SeatEY object  
	 * 
	 * <p>
	 * A <code>ShowSeat</code> object used to store all the parameters
	 * of the Show Seats
	 * </p>
	 * 
	 * 
	 */
public class ShowSeatEY extends SeatEY {

	private boolean occupied;

	public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
	/**
	 * The constructor of Show Seat (requires 4 parameters)
	 * @param seatID The seatID
	 * @param seatRow The seat row
	 * @param seatNumber The seat number
	 * @param seatType The seat type 
	 */
    public ShowSeatEY(String seatID, String seatRow, int seatNumber, String seatType) {
        super(seatID, seatRow, seatNumber, seatType);
        this.occupied=false;
	}
    /**
     * The constructor of show seat (requires 5 parameters)
     * @param seatID The seatID
     * @param seatRow The seat row
     * @param seatNumber The seat number
     * @param seatType The seat type
     * @param occupied True if occupied False if not occupied
     */
    public ShowSeatEY(String seatID, String seatRow, int seatNumber, String seatType, boolean occupied) {
        super(seatID, seatRow, seatNumber, seatType);
        this.occupied=occupied;
	}
    
    /**
     * Method to check if a seat is occupied
     * @return True if occupied,False if not occupied
     */
    public boolean isOccupied(){
        return this.occupied;
    }

}