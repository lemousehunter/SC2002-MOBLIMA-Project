import java.util.ArrayList;

public class StaffManager implements Manager {

  private ArrayList<User> masterUserList;
  private ArrayList<CineplexEY> masterCineplexes;
  private ArrayList<ScreenEY> masterScreens;
  private ArrayList<BookingEY> masterBookings;
  private ArrayList<ShowEY> masterShows;
  private ArrayList<MovieEY> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ReviewE> masterRatings;

  private CineplexManager cineplexManager;
  private ScreenManager screenManager;
  private ShowManager showManager;
  private MovieManager movieManager;
  private StaffBoundary staffIO;

  private static StaffManager currentInstance;

  public StaffManager(
    CineplexManager cineplexMgr,
    ScreenManager screenMgr,
    ShowManager showManager,
    MovieManager movieMgr
  ) {
    this.cineplexManager = cineplexMgr;
    this.screenManager = screenMgr;
    this.showManager = showManager;
    this.movieManager = movieMgr;
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
    ArrayList<ReviewE> masterRatings
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
          cineplexOperations();
          break;
        case 2:
          screenOperations();
          break;
        case 3:
          break;
        case 4:
          break;
        case 5:
          break;
        case 7:
          break;
        case 8:
          break;
        case 9:
          break;
      }
    }
  }

  private void cineplexOperations() {
    int cineplexChoice = 0;
    while (cineplexChoice != 4) {
      cineplexChoice = staffIO.getCineplexMenuChoice();
      if (cineplexChoice < 0 | cineplexChoice > 4) {
        staffIO.printCineplexMenuChoiceError();
        continue;
      }
      switch (cineplexChoice) {
        case 1:
          cineplexManager.addCineplex();
          break;
        case 2:
          cineplexManager.searchCineplex();
          break;
        case 3:
          cineplexManager.listAllCineplexes();
          break;
        case 4:
          break;
      }
    }
  }

  private void screenOperations() {
    int screenChoice = 0;
    while (screenChoice != 4) {
      screenChoice = staffIO.getScreenMenuChoice();
      if (screenChoice < 0 | screenChoice > 4) {
        staffIO.printScreenMenuChoiceError();
        continue;
      }
      switch (screenChoice) {
        case 1:
          screenManager.addScreen();;
          break;
        case 2:
          screenManager.searchScreen();
          break;
        case 3:
          screenManager.listAllScreens();
          break;
        case 4:
          break;
      }
    }
  }
}
