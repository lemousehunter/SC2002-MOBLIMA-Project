/**
 * A Ticket Price  Object
 * 
 * <p>
 * A <code>TicketPrice</code> object used 
 * to store all parameters about ticket price
 * </p>
 * 
 */
public class TicketPrice {
    
    private DayTypeEN dayType;
    private ScreenClassEN screenClass;
    private MovieGoerAgeEN movieGoerAge;
    private MovieTypeEN movieType;

    private double price;
    
    /** 
     * Method to get the day type
     * @return DayTypeEN the day type
     */
    public DayTypeEN getDayType() {
        return dayType;
    }

    
    /** 
     * Method to get the screen class type
     * @return ScreenClassEN the type of screen class (PLATINUM_MOVIE_SUITES or REGULAR_SCREEN)
     */
    public ScreenClassEN getScreenClass() {
        return screenClass;
    }

    
    /** 
     * Method to get the movie goer age group
     * @return MovieGoerAgeEN the type of movie goer age group (ADULT,SENIOR,CHILD or STUDENT)
     */
    public MovieGoerAgeEN getMovieGoerAge() {
        return movieGoerAge;
    }

    
    /** 
     * Method to get the type of movie
     * @return MovieTypeEN the type of movie(BLOCKBUSTER,THREEDIMENSION or DOCUMENTARY)
     */
    public MovieTypeEN getMovieType() {
        return movieType;
    }
    
    /** 
     * Method to get ticket price
     * @return double the price of the ticket
     */
    public double getPrice() {
        return price;
    }

    
    /** 
     * Method to set the price of the ticket
     * @param price the price of the ticket
     */
    public void setPrice(double price) {
        this.price = price;
    }


    /**
     * The constructor of TicketPrice object
     * @param dayType The day type
     * @param screenClass The screen class type
     * @param moviegoerAge The movie goer age group
     * @param movieType The movie type
     * @param price The price of the ticket
     */
    public TicketPrice(DayTypeEN dayType, ScreenClassEN screenClass, MovieGoerAgeEN moviegoerAge, MovieTypeEN movieType, double price) {
		this.dayType = dayType;
		this.screenClass = screenClass;
		this.movieGoerAge = moviegoerAge;
		this.movieType = movieType;
		this.price = price;
	}

}
