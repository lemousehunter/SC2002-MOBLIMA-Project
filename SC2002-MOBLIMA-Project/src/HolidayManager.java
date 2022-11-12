import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HolidayManager extends Manager implements BaseManager {


  private ArrayList<String> masterHolidaysList;

  public HolidayManager() {
  }

  @Override
  public void setManagers() {
    CentralManagerEY centralMgr = this.getCentralManager();
  }

  @Override
  public void setMasterLists() {
    CentralManagerEY centralMgr = this.getCentralManager();
    this.masterHolidaysList = centralMgr.getMasterHolidaysList();
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

  public Integer addHoliday(String holidayDate) {
    if (this.masterHolidaysList.contains(holidayDate)) {
      return -1; // duplicateError
    }

    // Validate DD-MM-YYYY
    SimpleDateFormat format = new SimpleDateFormat(" DD-MM-YYY");
    // With lenient parsing, the parser may use heuristics to interpret
    // inputs that do not precisely match this object's format.
    try {
      format.parse(holidayDate);
    } catch (ParseException e) {
      return 0; // ParseError
    }
    this.masterHolidaysList.add(holidayDate);
    return 1; // success
  }

  public ArrayList<String> listAllHolidays() {
    return this.masterHolidaysList;
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
