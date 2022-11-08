import java.text.ParseException;

public class TicketPriceDefault {
	private MovieManager movieMgr;
	private double price;
	private String movieID;
	private MovieGoerManager movieGoerMgr;
	private String userID;
	private screenManager screenMgr;
	private String screenID;
	private String date;

	/**
	 *
	 * @param movieID
	 * @param userID
	 * @param screenID
	 */
	public TicketPriceDefault(String movieID, String userID, String screenID, String date) {
		this.movieMgr = new MovieManager();
		this.movieID = movieID;
		this.movieGoerMgr = new MovieGoerManager();
		this.userID = userID;
		this.screenMgr = new screenManager();
		this.screenID = screenID;
		this.date = date;

	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public double computePrice() throws ParseException {
		Holidays holidays = new Holidays();
		Boolean isHoliday = holidays.isHoliday(this.date); // 1: holiday, 0: not holiday
		Boolean isWeekend = holidays.getWeekend(this.date); // 1: weekend, 0: weekday
		boolean isSpecial = isHoliday || isWeekend; // 1: special, 0: weekday

		Movie movie = this.movieMgr.getMovieByID(this.movieID);
		Screen screen = this.screenMgr.getScreenByID(this.screenID);
		Boolean HallType = screen.getBooleanScreenType(); // 1: Premium Hall, 0: Regular Hall

		MovieGoer movieGoer = movieGoerMgr.getUserByID(this.userID);

		String movieGoerAge = movieGoer.getAgeType(); // 1: discounted price, 0: normal price

		double price;

		if (movie.getBoolType()){ // 1: blockbuster, 0: regular movies
			price = 15.0;
		}
		else{
			price = 10.0;
		}

		if (isSpecial){
			price *= 1.5;
		}

		if (HallType){
			price *= 2;
		}

		if (HallType){
			price *= 1;
		}
		if(movieGoerAge.equals(MovieGoerAge.STUDENT.toString()) || movieGoerAge.equals(MovieGoerAge.SENIOR.toString())){
			price *= 0.5;
		}
		if(movieGoerAge.equals(MovieGoerAge.CHILD.toString())){
			price = 0;
		}

		return price;
	}
}