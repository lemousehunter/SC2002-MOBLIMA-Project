//package com.moblima.app;

public class ShowSeat extends Seat{

	private boolean occupied;

	public ShowSeat( String seatID, String seatRow, int seatNumber, String seatType) {
        super(seatID, seatRow, seatNumber, seatType);
        this.occupied=false;
	}
    public ShowSeat(String seatID, String seatRow, int seatNumber, String seatType,boolean occupied) {
        super(seatID, seatRow, seatNumber, seatType);
        this.occupied=occupied;
	}
    
    public boolean isOccupied(){
        return this.occupied;
    }

}