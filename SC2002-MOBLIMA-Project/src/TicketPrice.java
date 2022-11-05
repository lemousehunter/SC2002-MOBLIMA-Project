public class TicketPrice {

	private DayType dayType;
	private ScreenClass screenClass;
	private MovieGoerAge movieGoerAge;
	private MovieType movieType;
	private double price;

	/**
	 * 
	 * @param dayType
	 * @param screenClass
	 * @param moviegoerAge
	 * @param movieType
	 * @param price
	 */
	public TicketPrice(DayType dayType, ScreenClass screenClass, MovieGoerAge moviegoerAge, MovieType movieType, double price) {
		this.dayType = dayType;
		this.screenClass = screenClass;
		this.movieGoerAge = moviegoerAge;
		this.movieType = movieType;
		this.price = price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}