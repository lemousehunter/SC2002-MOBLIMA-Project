public class TicketPrice {

	private DayType dayType;
	private ScreenClass screenClass;
	private MovieGoerAge movieGoerAge;
	private MovieType movieType;
	private int price;

	/**
	 * 
	 * @param dayType
	 * @param screenClass
	 * @param moviegoerAge
	 * @param movieType
	 * @param price
	 */
	public TicketPrice(DayType dayType, ScreenClass screenClass, MovieGoerAge moviegoerAge, MovieType movieType, int price) {
		this.dayType = dayType;
		this.screenClass = screenClass;
		this.movieGoerAge = moviegoerAge;
		this.movieType = movieType;
		this.price = price;
		throw new UnsupportedOperationException();
	}

	public DayType getDayType() {
		return dayType;
	}

	public void setDayType(DayType day){
		this.dayType = day;
	}

	public ScreenClass getScreenClass() {
		return this.screenClass;
	}

	public void setScreenClass(ScreenClass screen) {
		this.screenClass = screen;
	}

	public MovieGoerAge getMovieGoerAge() {
		return this.movieGoerAge;
	}

	public void setMovieGoerAge(MovieGoerAge moviegoer) {
		this.movieGoerAge = moviegoer;
	}

	public MovieType getMovieType() {
		return this.movieType;
	}

	public void setMovieType(MovieType movieType) {
		this.movieType = movieType;
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double finalPrice() { //A temporary way to come up with the ticket prices for now. Ideally, we will use a equation to compute the ticket prices
		if(this.screenClass==ScreenClass.PLATINUM_MOVIE_SUITES) {
			if(this.movieGoerAge==MovieGoerAge.ADULT)
				return 25;
			else
				return 16;
		}
		else {
			if(this.movieType==MovieType.BLOCKBUSTER){
				if(this.dayType==DayType.WEEKDAY)
					return 14.5;
				else
					return 15.5;
			}
			else if(this.movieType==MovieType.THREEDIMENSION){
				if(this.movieGoerAge==MovieGoerAge.STUDENT){
					if(this.dayType==DayType.WEEKDAY)
						return 8;
					else
						return 10;
				}
				else if(this.movieGoerAge==MovieGoerAge.ADULT){
					if(this.dayType==DayType.WEEKDAY)
						return 12.5;
					else
						return 15.5;
				}
				else{
					if(this.dayType==DayType.WEEKDAY)
						return 7;
					else
						return 9;
				}
			}
			else{
				if(this.movieGoerAge==MovieGoerAge.STUDENT){
					if(this.dayType==DayType.WEEKDAY)
						return 7;
					else
						return 9;
				}
				else if(this.movieGoerAge==MovieGoerAge.ADULT){
					if(this.dayType==DayType.WEEKDAY)
						return 10;
					else
						return 13;
				}
				else{
					if(this.dayType==DayType.WEEKDAY)
						return 5;
					else
						return 7;
				}
			}
		}
	}
}
