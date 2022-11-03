//package com.moblima.app;

public class ShowSeat extends Seat{

	private boolean occupied;

	public ShowSeat( String seatID, String seatRow, int seatNumber, String seatType) {
		// TODO - implement ShowSeat.ShowSeat
        super(seatID, seatRow, seatNumber, seatType);
        this.occupied=false;
	}
    public boolean isOccupied(){
        return this.occupied;
    }

}