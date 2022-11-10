import java.util.ArrayList;

public class SeatManager implements Manager{
    private ArrayList<User> masterUserList;
    private ArrayList<Cineplex> masterCineplexes;
    private ArrayList<Screen> masterScreens;
    private ArrayList<Booking> masterBookings;
    private ArrayList<Show> masterShows;
    private ArrayList<Movie> masterMovies;
    private ArrayList<String> masterHolidaysList;
    private ArrayList<ViewerRatings> masterRatings;
    private ArrayList<ShowSeat> masterShowSeats;

    @Override
    public void setMasterLists(ArrayList<User> masterUserList, ArrayList<Cineplex> masterCineplexes, ArrayList<Screen> masterScreens, ArrayList<Booking> masterBookings, ArrayList<Show> masterShows, ArrayList<Movie> masterMovies, ArrayList<String> masterHolidaysList, ArrayList<ViewerRatings> masterRatings, ArrayList<ShowSeat> masterShowSeats) {
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

    public Seat getSeatByID(String seatID) {
        for (Seat seat: masterShowSeats) {
            if (seat.getSeatID().equals(seatID)) {
                return seat;
            }
        }
        return null;
    }

}
