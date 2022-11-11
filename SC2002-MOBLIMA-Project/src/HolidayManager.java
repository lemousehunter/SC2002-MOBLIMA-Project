import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HolidayManager implements Manager {

  private ArrayList<User> masterUserList;
  private ArrayList<CineplexEY> masterCineplexes;
  private ArrayList<ScreenEY> masterScreens;
  private ArrayList<BookingEY> masterBookings;
  private ArrayList<ShowEY> masterShows;
  private ArrayList<MovieEY> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ReviewEY> masterRatings;

  private HolidayBoundary holidayIO;

  public HolidayManager() {
    holidayIO = new HolidayBoundary();
  }

  @Override
  public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<CineplexEY> masterCineplexes,
    ArrayList<ScreenEY> masterScreens,
    ArrayList<BookingEY> masterBookings,
    ArrayList<ShowEY> masterShows,
    ArrayList<MovieEY> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ReviewEY> masterRatings
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

  public void addHoliday() {
    String holidayDate = holidayIO.setHolidayDate();
    for (String holiday : masterHolidaysList) {
      if (holiday.equals(holidayDate)) {
        holidayIO.printHolidayDuplicateError();
        return;
      }
    }

    String holiday = holidayDate;
    // Validate DD-MM-YYYY
    SimpleDateFormat format = new SimpleDateFormat(" DD-MM-YYY");
    // With lenient parsing, the parser may use heuristics to interpret
    // inputs that do not precisely match this object's format.
    Boolean validDate = true;
    try {
      format.parse(holiday);
    } catch (ParseException e) {
      validDate = false;
    }

    if (!validDate){
      holidayIO.printInvalidDate();
      return;
    }

    masterHolidaysList.add(holiday);
    holidayIO.printAddHolidaySuccessMessaage();
  }

  public void listAllHolidays() {
    holidayIO.printAllHolidays(masterHolidaysList);
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
