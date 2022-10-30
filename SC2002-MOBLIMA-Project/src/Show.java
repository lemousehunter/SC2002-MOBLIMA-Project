public class Show {

	private ShowDate showDate;
	private int  showTime;
	private Movie movie;
	private Screen screen;
	private int emptySeats;

	/**
	 * 
	 * @param showDate
	 * @param showTime
	 */
	public Show(ShowDate showDate, int showTime) {
		// TODO - implement Show.Show
		this.showDate=showDate;
		this.showTime=showTime;

	}

	public Movie getMovie() {
		return this.movie;
	}

	/**
	 * 
	 * @param movie
	 */
	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	/**
	 * 
	 * @param showDate
	 * @param showTime
	 * @param screen
	 */

	/**
	 * 
	 * @param movie
	 */
	public void assignMovie(Movie movie) {
		// TODO - implement Show.assignMovie
		throw new UnsupportedOperationException();
	}

	public int getEmptySeats() {
		return this.emptySeats;
	}

	/**
	 * 
	 * @param emptySeats
	 */
	public void setEmptySeats(int emptySeats) {
		this.emptySeats = emptySeats;
	}

	public void ShowSeatLayout() {
		// TODO - implement Show.ShowAvailableSeats
		throw new UnsupportedOperationException();
	}

}