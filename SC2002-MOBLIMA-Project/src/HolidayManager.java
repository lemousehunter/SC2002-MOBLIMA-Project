import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HolidayManager implements Manager {

  private ArrayList<User> masterUserList;
  private ArrayList<Cineplex> masterCineplexes;
  private ArrayList<Screen> masterScreens;
  private ArrayList<Booking> masterBookings;
  private ArrayList<Show> masterShows;
  private ArrayList<Movie> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ViewerRatings> masterRatings;

  public HolidayManager() {
  }

  @Override
  public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<Cineplex> masterCineplexes,
    ArrayList<Screen> masterScreens,
    ArrayList<Booking> masterBookings,
    ArrayList<Show> masterShows,
    ArrayList<Movie> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ViewerRatings> masterRatings
  ) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;

  }
 

  public boolean isHoliday(String date) {
    return this.masterHolidaysList.contains(date);
  }

  public Boolean getWeekend(String date) throws ParseException {
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    LocalDate date_dt = LocalDate.parse(date, dtFormatter);
    DayOfWeek day = date_dt.getDayOfWeek();
    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
  }
  /*  public static void main(String[] args) testing purposes
    {
        Ratings r=new Ratings();
        System.out.println(r.getAllRatingInfo());
        System.out.println(r.getMovieName());
        System.out.println(r.getReview());
        System.out.println(r.getReviewerName());
        System.out.println(r.getRating());
    }
    */
}
