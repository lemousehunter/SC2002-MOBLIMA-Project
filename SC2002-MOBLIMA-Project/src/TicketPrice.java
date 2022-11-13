public class TicketPrice {
    
    private DayTypeEN dayType;
    private ScreenClassEN screenClass;
    private MovieGoerAgeEN movieGoerAge;
    private MovieTypeEN movieType;

    private double price;
    public DayTypeEN getDayType() {
        return dayType;
    }

    public ScreenClassEN getScreenClass() {
        return screenClass;
    }

    public MovieGoerAgeEN getMovieGoerAge() {
        return movieGoerAge;
    }

    public MovieTypeEN getMovieType() {
        return movieType;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public TicketPrice(DayTypeEN dayType, ScreenClassEN screenClass, MovieGoerAgeEN moviegoerAge, MovieTypeEN movieType, double price) {
		this.dayType = dayType;
		this.screenClass = screenClass;
		this.movieGoerAge = moviegoerAge;
		this.movieType = movieType;
		this.price = price;
	}

}
