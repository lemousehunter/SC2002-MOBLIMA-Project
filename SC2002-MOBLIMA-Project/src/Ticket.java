import java.text.ParseException;

public class Ticket {
    private MovieManager movieMgr;
    private double price;
    private String movieID;
    private MovieGoerManager movieGoerMgr;
    private String userID;
    private ScreenManager screenMgr;
    private String screenID;
    private String date;
    private String seatId;



    private HolidayManager holidayManager;
    private String bookingID;

    /**
     *
     * @param movieID
     * @param userID
     * @param screenID
     */
    public Ticket(String movieID, String userID, String screenID, String date, String seatId, double price, String  bookingID, HolidayManager holidayManager, MovieManager movieMgr) throws ParseException {
        // Passing in controller instances from MainApp
        this.movieMgr = movieMgr;
        this.movieGoerMgr = new MovieGoerManager();
        this.screenMgr = new ScreenManager();
        this.holidayManager = holidayManager;

        // Class attributes
        this.movieID = movieID;
        this.userID = userID;
        this.screenID = screenID;
        this.date = date;
        this.seatId=seatId;
        this.price = price;
        this.bookingID = bookingID;

        if (this.price == -1.0) { // if price was not set, automatically sets it
            this.price = this.computePrice();
        }
    }

    public String getMovieID() {
        return this.movieID;
    }

    public String getUserID() {
        return this.userID;
    }

    public String getScreenID() {
        return  this.screenID;
    }

    public String getDate() {
        return this.date;
    }
    
    public String getSeatId() {
        return seatId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }


    public double computePrice() throws ParseException {
        Boolean isHoliday = this.holidayManager.isHoliday(this.date); // 1: holiday, 0: not holiday
        Boolean isWeekend = this.holidayManager.getWeekend(this.date); // 1: weekend, 0: weekday
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
