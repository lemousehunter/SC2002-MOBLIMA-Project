import java.util.ArrayList;
import java.util.Scanner;

public class StaffManager implements Manager {

  private ArrayList<User> masterUserList;
  private ArrayList<Cineplex> masterCineplexes;
  private ArrayList<Screen> masterScreens;
  private ArrayList<Booking> masterBookings;
  private ArrayList<Show> masterShows;
  private ArrayList<Movie> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ViewerRatings> masterRatings;

  private CineplexManager cineplexManager;
  private screenManager screenManager;
  private ShowManager showManager;
  private MovieManager movieManager;
  private StaffBoundary staffIO;

  private static StaffManager currentInstance;

  public StaffManager(
    CineplexManager cineplexMgr,
    screenManager screenMgr,
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

  /**
   *
   * @param ticket
   */
  public void setTicketPrices(Ticket ticket) {
    // TODO - implement Staff.setTicketPrices
    throw new UnsupportedOperationException();
  }

  /**
   *
   * @param name
   * @param type
   * @param movieRating
   * @param showStatus
   * @param synopsis
   * @param director
   * @param cast
   */
  public void addMovies(
    int name,
    int type,
    int movieRating,
    int showStatus,
    int synopsis,
    int director,
    int cast
  ) {
    // TODO - implement Staff.addMovies

    throw new UnsupportedOperationException();
  }

  public void getTop5RankingMoviesByTicketSales() {
    // TODO - implement Staff.getTop5RankingMoviesByTicketSales
    throw new UnsupportedOperationException();
  }

  public void getTop5RakingMoviesByOverallReviews() {
    // TODO - implement Staff.getTop5RakingMoviesByOverallReviews
    throw new UnsupportedOperationException();
  }

  /**
   *
   * @param movie
   */
  public void updateMovie(Movie movie) {
    // TODO - implement Staff.updateMovie
    throw new UnsupportedOperationException();
  }

  /**
   *
   * @param cineplex
   */
  public void addCinePlex(Cineplex cineplex) {
    // TODO - implement Staff.addCinePlex
    throw new UnsupportedOperationException();
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
        case 2:
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
}
