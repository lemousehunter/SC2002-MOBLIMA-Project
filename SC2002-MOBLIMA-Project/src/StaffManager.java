import java.util.ArrayList;

public class StaffManager implements BaseManager {

  private ArrayList<User> masterUserList;
  private ArrayList<CineplexEY> masterCineplexes;
  private ArrayList<ScreenEY> masterScreens;
  private ArrayList<BookingEY> masterBookings;
  private ArrayList<ShowEY> masterShows;
  private ArrayList<MovieEY> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ReviewEY> masterRatings;

  private CineplexManager cineplexManager;
  private ScreenManager screenManager;
  private ShowManager showManager;
  private MovieManager movieManager;
  private HolidayManager holidayManager;
  private TicketPriceManager ticketPriceManager;
  private ReviewManager reviewManager;
  private BookingManager bookingManager;
  private StaffBoundary staffIO;

  private static StaffManager currentInstance;

  public StaffManager(
    CineplexManager cineplexMgr,
    ScreenManager screenMgr,
    ShowManager showManager,
    MovieManager movieMgr,
    HolidayManager holidayManager,
    TicketPriceManager ticketPriceManager,
    ReviewManager reviewManager,
    BookingManager bookingManager
  ) {
    this.cineplexManager = cineplexMgr;
    this.screenManager = screenMgr;
    this.showManager = showManager;
    this.movieManager = movieMgr;
    this.holidayManager = holidayManager;
    this.ticketPriceManager = ticketPriceManager;
    this.reviewManager = reviewManager;
    this.bookingManager=bookingManager;
    staffIO = new StaffBoundary();
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


  public void staffOperations() {
    int choice = 0;
    while (choice != 9) {
      choice = staffIO.getMainMenuChoice();
      if (choice < 0 | choice > 9) {
        staffIO.printMainMenuChoiceError();
        continue;
      }
      switch (choice) {
        case 1:
          this.cineplexBoundary.cineplexOperations();
          break;
        case 2:
          screenOperations();
          break;
        case 3:
          movieOperations();
          break;
        case 4:
          showOperations();
          break;
        case 5:
          ticketPriceOperations();
          break;
        case 6:
          this.holidayBoundary.holidayOperations();
          break;
        case 7:
          ArrayList<String> top5Movies = bookingManager.getTop5Movies();
          System.out.println("Top 5 movies by bookings:");
          for (String moviename : top5Movies){
            System.out.println(moviename);
          }
          break;
        case 8:
          reviewManager.top5MoviesByViewerRatings();
          break;
        case 9:
          break;
      }
    }
  }

  private void showOperations() {
  }

  
  private void ticketPriceOperations() {
    int ticketPriceChoice = 0;
    while (ticketPriceChoice != 3) {
      ticketPriceChoice = staffIO.getTicketPriceMenuChoice();
      if (ticketPriceChoice < 0 | ticketPriceChoice > 3) {
        this.holidayBoundary.printHolidayMenuChoiceError();
        continue;
      }
      switch (ticketPriceChoice) {
        case 1:
          ticketPriceManager.addTicketPrice();
          break;
        case 2:
          ticketPriceManager.listAllTicketPrices();
          break;
        case 3:
          break;
      }
    }
  }
}
