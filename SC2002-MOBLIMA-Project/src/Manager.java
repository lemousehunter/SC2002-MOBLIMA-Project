import java.util.ArrayList;

public interface Manager {
  ArrayList<User> masterUserList = null;
  ArrayList<CineplexEY> masterCineplexes = null;
  ArrayList<ScreenEY> masterScreens = null;
  ArrayList<BookingEY> masterBookings = null;
  ArrayList<ShowEY> masterShows = null;
  ArrayList<MovieEY> masterMovies = null;
  ArrayList<String> masterHolidaysList = null;
  ArrayList<ReviewE> masterRatings = null;
  ArrayList<ShowSeatEY> masterShowSeats = null;

  void setMasterLists(ArrayList<User> masterUserList, ArrayList<CineplexEY> masterCineplexes, ArrayList<ScreenEY> masterScreens, ArrayList<BookingEY> masterBookings, ArrayList<ShowEY> masterShows, ArrayList<MovieEY> masterMovies, ArrayList<String> masterHolidaysList, ArrayList<ReviewE> masterRatings, ArrayList<ShowSeatEY> masterShowSeats);
}
