import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TicketPriceManager implements Manager {

  private ArrayList<User> masterUserList;
  private ArrayList<CineplexEY> masterCineplexes;
  private ArrayList<ScreenEY> masterScreens;
  private ArrayList<BookingEY> masterBookings;
  private ArrayList<ShowEY> masterShows;
  private ArrayList<MovieEY> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ReviewEY> masterRatings;
  private ArrayList<TicketPrice> masterTicketPrices;


  private TicketPriceBoundary ticketPriceIO;

  public TicketPriceManager () {
    ticketPriceIO = new TicketPriceBoundary();
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

  public void setMasterTicketPrices(ArrayList<TicketPrice> masterTicketPrices){
    this.masterTicketPrices = masterTicketPrices;
  }

  public void addTicketPrice() {
    String dateType = ticketPriceIO.setDayType();
    String screenClass = ticketPriceIO.setScreenClass();
    String movieGoerAge= ticketPriceIO.setMovieGoerAge();
    String movieType = ticketPriceIO.setMovieType();
    double price=ticketPriceIO.setPrice();

    TicketPrice saveTicketPrice = null;

    for (TicketPrice ticketPrice : masterTicketPrices) {
      if (ticketPrice.getDayType().toString().equals(dateType) &&
          ticketPrice.getScreenClass().toString().equals(screenClass) &&
          ticketPrice.getMovieGoerAge().toString().equals(movieGoerAge) &&
          ticketPrice.getMovieType().toString().equals(movieType)) {
            saveTicketPrice = ticketPrice; 
            break;
          }
    }
    if (saveTicketPrice == null){
      TicketPrice t = new TicketPrice(DayTypeEN.valueOf(dateType), ScreenClassEN.valueOf(screenClass) ,MovieGoerAgeEN.valueOf(movieGoerAge), MovieTypeEN.valueOf(movieType), price);
      masterTicketPrices.add(t);
      ticketPriceIO.printAddTicketPriceSuccessMessaage();  
    }
    else {
      saveTicketPrice.setPrice(price);
      ticketPriceIO.printUpdateTicketPriceSuccessMessaage();
    }

  }

  public void listAllTicketPrices() {
    ticketPriceIO.printAllTicketPrices(masterTicketPrices);
  }
  
}
