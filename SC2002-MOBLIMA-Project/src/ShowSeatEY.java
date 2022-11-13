//package com.moblima.app;

public class ShowSeatEY extends SeatEY {

	private boolean occupied;

	public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    public ShowSeatEY(String seatID, String seatRow, int seatNumber, String seatType) {
        super(seatID, seatRow, seatNumber, seatType);
        this.occupied=false;
	}
    public ShowSeatEY(String seatID, String seatRow, int seatNumber, String seatType, boolean occupied) {
        super(seatID, seatRow, seatNumber, seatType);
        this.occupied=occupied;
	}
    
    public boolean isOccupied(){
        return this.occupied;
    }

}