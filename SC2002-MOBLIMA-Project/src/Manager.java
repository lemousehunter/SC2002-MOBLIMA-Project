import java.util.ArrayList;

public interface Manager {
  ArrayList<User> masterUserList = null;
  ArrayList<Cineplex> masterCineplexes = null;
  ArrayList<Screen> masterScreens = null;
  ArrayList<Booking> masterBookings = null;
  ArrayList<Show> masterShows = null;
  ArrayList<Movie> masterMovies = null;
  ArrayList<String> masterHolidaysList = null;
  ArrayList<ViewerRatings> masterRatings = null;

  public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<Cineplex> masterCineplexes,
    ArrayList<Screen> masterScreens,
    ArrayList<Booking> masterBookings,
    ArrayList<Show> masterShows,
    ArrayList<Movie> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ViewerRatings> masterRatings
  );
  
}
