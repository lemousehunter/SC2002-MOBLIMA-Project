import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketEY {
    private MovieManager movieMgr;
    private double price;
    private String movieID;
    private MovieGoerManager movieGoerMgr;
    private String userID;
    private ScreenManager screenMgr;
    private String screenID;
    private String date;
    private String time;
    private String seatId;


    private HolidayManager holidayManager;
    private String bookingID;

    /**
     * @param movieID
     * @param userID
     * @param screenID
     * @param time
     */
    public TicketEY(String movieID, String userID, String screenID, String date, String time, String seatId, double price, String  bookingID, HolidayManager holidayManager, MovieManager movieMgr) throws ParseException {
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
        this.time = time;
        this.seatId = seatId;
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

    public String getTime() { return this.time; }
    
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
        boolean isHoliday = this.holidayManager.isHoliday(this.date); // 1: holiday, 0: not holiday
        boolean isWeekend = this.holidayManager.getWeekend(this.date); // 1: weekend, 0: weekday
        boolean isSpecialDay = isHoliday || isWeekend; // 1: special, 0: weekday
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date time = sdf.parse(this.time);
        boolean isBefore6 = (time.before(sdf.parse("18:00")));

        MovieEY movie = this.movieMgr.getMovieByID(this.movieID);
        ScreenEY screen = this.screenMgr.getScreenByID(this.screenID);
        boolean HallType = screen.getBooleanScreenType(); // 1: Premium Hall, 0: Regular Hall

        MovieGoerEY movieGoer = movieGoerMgr.getUserByID(this.userID);

        String movieGoerAge = movieGoer.getAgeType(); // 1: discounted price, 0: normal price

        double price;

        if (movie.getBoolType()){ // 1: blockbuster, 0: regular movies
            price = 15.0;
        }
        else{
            price = 10.0;
        }

        if (isSpecialDay){
            price *= 1.5; // higher prices charged for shows on weekends
        }
        else {
            if (isBefore6) {
                price *= 0.8; // discount for shows before 6pm on non-weekends and non-holidays
            }
        }

        if (HallType){
            price *= 2; // double price for premium halls
        }

        if(movieGoerAge.equals(MovieGoerAgeEN.STUDENT.toString()) || movieGoerAge.equals(MovieGoerAgeEN.SENIOR.toString())){
            price *= 0.5; // half price for students and seniors
        }
        if(movieGoerAge.equals(MovieGoerAgeEN.CHILD.toString())){
            price = 0; // FOC entrance for children
        }

        return price;
    }
}
