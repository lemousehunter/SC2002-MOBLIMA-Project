import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BookingBoundary extends Boundary implements BaseBoundary{
    // Managers
    BookingManager bookingManager;
    ScreenManager screenManager;
    CineplexManager cineplexManager;
    MovieManager movieManager;
    ShowManager showManager;

    // Boundaries
    CineplexBoundary cineplexBoundary;
    MovieBoundary movieBoundary;
    ShowBoundary showBoundary;



    public BookingBoundary() {
    }

    @Override
    public void setManagers() {
        CentralManagerEY centralManager = this.getCentralManager();
        this.bookingManager = centralManager.getBookingMgr();
        this.screenManager = centralManager.getScreenMgr();
        this.cineplexManager = centralManager.getCineplexMgr();
        this.movieManager = centralManager.getMovieMgr();
        this.showManager = centralManager.getShowMgr();

    }

    @Override
    public void setBoundaries() {
        CentralManagerEY centralManager = this.getCentralManager();
        this.cineplexBoundary = centralManager.getCineplexBoundary();
        this.movieBoundary = centralManager.getMovieBoundary();
        this.showBoundary = centralManager.getShowBoundary();
    }

    public String getCineplexChoice() {
        this.print("Please enter the corresponding index of the cinema you would like to visit");
        this.cineplexBoundary.printAllCineplexes();
        return this.cineplexManager.convertIDX2CineplexID(this.getScanner().nextInt());
    }

    public String getMovieChoice() { // converts idx from user input to movieID
        int choice = this.getInputInt(
                "\n Would you like to: \n" +
                "1) View Movie Details\n" +
                "2) Book Movie?\n"
                );
        if (choice == 1) {
            this.println("Which movie would you like to view details for? Please enter the corresponding integer.");
            this.movieBoundary.printMovieList();
            int viewMovie = this.getScanner().nextInt();
            String movieID = this.movieManager.getMovieIDFromCurrentShowingIDX(viewMovie);
            this.movieBoundary.viewMovieDetails(movieID);
            this.getMovieChoice(); // calls itself again to emulate jumping back to prev menu
        }
        else {
            this.print("Which movie would you like to book for? Please enter the corresponding integer.");
            this.movieBoundary.printMovieList();
            int idx = this.getScanner().nextInt();
            return this.movieManager.getMovieIDFromCurrentShowingIDX(idx);
        }
        return "";
    }

    public String getShowDate(String cineplex, String movieID) { // prints out available show dates, gets show date from user
        this.print("Please enter the corresponding index of the date you would like to book");
        this.showBoundary.printShowDates(cineplex,movieID);
        int viewShow = this.getScanner().nextInt();
        return this.showManager.getShowIDFromShowDateIDX(cineplex,movieID,viewShow);
    }

    public ArrayList<String> getShowTime(String cineplex, String movieID, String showDate) { // prints out available show timings, returns showTime, screenID
        this.print("Please enter the corresponding index of the time you would like to book");
        this.showBoundary.printShowTimes(cineplex,movieID,showDate);
        int viewShow = this.getScanner().nextInt();
        return this.showManager.getShowIDFromShowTimeIDX(cineplex,movieID,showDate,viewShow);    }

    public Integer getNumTickets() {
        return this.getInputInt("How many tickets would you like to book? ");
    }

    // public String getShowSeat(int i) { // prints out Avail seat layout and gets seat based on seatNumber
    //     // TODO: 13/11/22 Print Avail Seat Layout
    //     return this.getInputLine("Please choose a seat for ticket " + (i+1));

    // }

    public void showBooking(String bookingID) {
        BookingEY booking = bookingManager.getBookingByID(bookingID);

        this.println("================================");
        this.println("Booking: " + bookingID);
        this.println("--------------------------------");
        this.println("UserID: " + booking.getUserID());
        this.println("Movie: " + this.movieManager.getMovieByID(booking.getMovieID()).getName());
        this.println("Screen: " + this.screenManager.getScreenByID(booking.getScreenID()).getScreenName());
        this.println("Cinema: " + this.cineplexManager.getCineplexByID(booking.getCinemaID()).getName());
        this.println("Show Date: " + booking.getDate());
        this.println("Show Time: " + booking.getTime());
        this.println("Total Paid: " + booking.getBookingAmount());
        this.println("--------------------------------");
        this.println("Seats");
        this.println("--------------------------------");
        ArrayList<String> seats = this.bookingManager.getListOfSeats(booking);
        for (String seat: seats) {
            this.println(seat);
        }
    }

    public void BookingOperations(String userID) throws ParseException {
        String cineplex = this.getCineplexChoice();
        String movieID = this.getMovieChoice();
        String showDate = this.getShowDate(cineplex, movieID);
        ArrayList<String> container = this.getShowTime(cineplex, movieID,showDate);
        String showTime = container.get(0);
        String screenID = container.get(1);
        String showID   = container.get(2);
        Integer numTickets = this.getNumTickets();

        
        ArrayList<String> seatIDs = this.getShowSeat(showID,numTickets);
        

        String bookingID = this.bookingManager.BookTicket(userID, movieID, showDate, showTime, cineplex, screenID, seatIDs);
        this.showManager.setSeatOccupied(showID, seatIDs);
        

        this.println("\nBooking ID : " + bookingID + " : Your Tickets have been booked successfully!!  Amount Charged : " + this.bookingManager.getBookingByID(bookingID).getBookingAmount() + "\n");
        
    }

    private ArrayList<String> getShowSeat(String showID, Integer numTickets) {
        this.showBoundary.ShowSeatLayout(showID);

        ArrayList<String> seatIDs = new ArrayList<String>();
        String seatID="";
        this.println("Please enter the seat numbers  : \n");

        for (int i=1 ; i <= numTickets;i++){

            seatID = this.getInputLine(i+". ");
            if (i == 1) {
                seatID =this.getScanner().nextLine();
            }
            seatIDs.add(seatID);
        }
        

        return new ArrayList<String>(seatIDs);
    }

}

