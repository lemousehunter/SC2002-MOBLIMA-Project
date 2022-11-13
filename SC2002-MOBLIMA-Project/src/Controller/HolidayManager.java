package Controller;

import Entity.CentralManagerEY;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A Controller.HolidayManager object
 * <p>
 * A <code>Controller.HolidayManager</code> object contains all the parameters and methods required
 * to communicate between entity and boundary of Holiday Class
 * </p>
 */

public class HolidayManager extends Manager implements BaseManager {

  // managers
  /**
   * Object of Class Controller.IoManager to manage I/O functions of the file
   */
  private IoManager ioManager;

  /**
   * Master array list of all holidays
   */
  private ArrayList<String> masterHolidaysList;

  /**
   * Default Constructor
   */
  public HolidayManager() {
  }

  /**
   * Method to set all controller classes passed as attributes
   */
  @Override
  public void setManagers() {
    CentralManagerEY centralMgr = this.getCentralManager();
    this.ioManager = centralMgr.getIoManager();

  }

  /**
   * Method to set all master lists passed as attributes
   */
  @Override
  public void setMasterLists() {
    CentralManagerEY centralMgr = this.getCentralManager();
    this.masterHolidaysList = centralMgr.getMasterHolidays();
  }

  /**
   * Method to check if the date passed is a holiday or not
   * @param date Date
   * @return True if the date is a holiday, False otherwise
   */
  public boolean isHoliday(String date) {
    return this.masterHolidaysList.contains(date);
  }

  
  /** 
   * Method to check if the date is a weekend
   * @param date Date
   * @return True if the date is a weekend, False otherwise
   * @throws ParseException
   */
  public Boolean getWeekend(String date) throws ParseException {
    DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDate date_dt = LocalDate.parse(date, dtFormatter);
    DayOfWeek day = date_dt.getDayOfWeek();
    return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
  }

  
  /** 
   * Method to add a holiday
   * @param holidayDate The date to be added as a holiday
   * @return 1 if date already exists as a holiday, 2 if the date format is wrong, 0 if the addition is a success
   */
  public int addHoliday(String holidayDate) {
    if (this.masterHolidaysList.contains(holidayDate)) {
      return 1; // duplicateError
    }

    // Validate DD-MM-YYYY
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    dateFormat.setLenient(false);
    Boolean validDate = true;
    try {
      dateFormat.parse(holidayDate);
    } catch (ParseException e) {
      return 2;
    }

    this.masterHolidaysList.add(holidayDate);
    return 0; // success
  }

  
  /** 
   * Method to return all holidays as an array
   * @return list of all holidays
   */
  public ArrayList<String> listAllHolidays() {
    ArrayList<String> printList = new ArrayList<String>();
    if (masterHolidaysList.size() > 0) {
      printList.add("\nList of Holidays configured : \n");
      for (String printline : this.masterHolidaysList){
        printList.add(printline);
      }
    }
    else{
      printList.add("No Holidays configured !");
    }
    return printList;
  }

  
  /**
   * Method to read the input data about holidays 
   * @throws IOException
   */
  public void primeHolidays() throws IOException {
    String filename = this.getCentralManager().getDataFolder().concat("Holidays.txt");

    ArrayList stringArray = null;
    try {
      stringArray = (ArrayList) ioManager.read(filename);
    } catch (FileNotFoundException e) {
        System.out.println("Priming of Holidays objects is skipped as there is no master data");
        return;
    }
    for (int i = 0; i < stringArray.size(); i++) {
        String st = (String) stringArray.get(i);
        // get individual 'fields' of the string separated by SEPARATOR
        StringTokenizer star = new StringTokenizer(st);// pass in the string to the string tokenizer using delimiter
                                                       // ","
        String holidayDate = star.nextToken().trim(); // first token

        this.masterHolidaysList.add(holidayDate);
    }
}

  
  /** 
   * Method to write back the data to the files
   * @throws IOException
   */
  public void writeHolidays() throws IOException {
    String filename = this.getCentralManager().getDataFolder().concat("Holidays.txt");
    List alw = new ArrayList();
    HolidayManager holidayMgr;
    for (int i = 0; i < this.masterHolidaysList.size(); i++) {
        String holiday = this.masterHolidaysList.get(i);
        StringBuilder st = new StringBuilder();
        st.append(holiday.trim());
        alw.add(st.toString());
    }
     ioManager.write(filename, alw);;

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
