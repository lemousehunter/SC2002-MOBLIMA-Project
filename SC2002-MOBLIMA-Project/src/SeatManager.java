import java.util.ArrayList;

public class SeatManager implements Manager{
    private ArrayList<User> masterUserList;
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private ArrayList<BookingEY> masterBookings;
    private ArrayList<ShowEY> masterShows;
    private ArrayList<MovieEY> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ReviewE> masterRatings;
    private ArrayList<ShowSeatEY> masterShowSeats;

    @Override
    public void setMasterLists(ArrayList<User> masterUserList, ArrayList<CineplexEY> masterCineplexes, ArrayList<ScreenEY> masterScreens, ArrayList<BookingEY> masterBookings, ArrayList<ShowEY> masterShows, ArrayList<MovieEY> masterMovies, ArrayList<String> masterHolidaysList, ArrayList<ReviewE> masterRatings, ArrayList<ShowSeatEY> masterShowSeats) {
        this.masterUserList = masterUserList;
        this.masterCineplexes = masterCineplexes;
        this.masterScreens = masterScreens;
        this.masterBookings = masterBookings;
        this.masterShows = masterShows;
        this.masterMovies = masterMovies;
        this.masterHolidaysList = masterHolidaysList;
        this.masterRatings = masterRatings;
        this.masterShowSeats = masterShowSeats;
    }

    public SeatEY getSeatByID(String seatID) {
        for (SeatEY seat: masterShowSeats) {
            if (seat.getSeatID().equals(seatID)) {
                return seat;
            }
        }
        return null;
    }

}
