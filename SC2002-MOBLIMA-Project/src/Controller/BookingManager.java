package Controller;

import Entity.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * A Controller.BookingManager object
 * <p>
 * A <code>Controller.BookingManager</code> object contains all the parameters and methods required
 * to communicate between entity and boundary of Booking Class
 * </p>
 */

public class BookingManager extends Manager implements BaseManager {
    /**
     * An object of Entity.TicketEY class entity
     */
    private TicketEY ticket;

    // Managers
    /**
     * Controller Class of Holidays
     */
    private HolidayManager holidayMgr;
    /**
     * Controller Class of Movies
     */
    private MovieManager movieMgr;
    /**
     * Controller Class of MovieGoers
     */
    private MovieGoerManager movieGoerMgr;

    private ScreenManager screenManager;

    /**
     * Object of Class Controller.IoManager to manage File I/O functions
     */

    private IoManager ioManager;
    private TicketPriceManager ticketPriceManager;

    // MasterLists
    /**
     * Master array list of all movie goers
     */
    private ArrayList<MovieGoerEY> masterMovieGoers;
    /**
     * Master array list of all bookings
     */
    private ArrayList<BookingEY> masterBookings;
    /**
     * Master array list of all movies
     */
    private ArrayList<MovieEY> masterMovies;

    /**
     * Constructor method to create an object of class Controller.BookingManager
     */
    public BookingManager() {

    }

    /**
     * Method to set all the controller classes passed as attributes
     */
    @Override
    public void setManagers() {
        this.holidayMgr = this.getCentralManager().getHolidayMgr();
        this.movieMgr = this.getCentralManager().getMovieMgr();
        this.movieGoerMgr = this.getCentralManager().getMovieGoerMgr();
        this.ioManager = this.getCentralManager().getIoManager();
        this.screenManager = this.getCentralManager().getScreenMgr();
        this.ticketPriceManager = this.getCentralManager().getTicketPriceMgr();
    }

    /**
     * Method to set all the master lists passed as attributes
     */
    @Override
    public void setMasterLists() {
        CentralManagerEY centralMgr = this.getCentralManager();
        this.masterBookings = centralMgr.getMasterBookings();
        this.masterMovies = centralMgr.getMasterMovies();
        this.masterMovieGoers = centralMgr.getMasterMovieGoers();
    }


    /**
     * Method to get the booking using booking ID
     *
     * @param bookingID The booking ID
     * @return The Entity.BookingEY object
     */
    public BookingEY getBookingByID(String bookingID) {
        for (BookingEY booking : this.masterBookings) {
            if (booking.getBookingID().equals(bookingID)) {
                return booking;
            }
        }
        return null;
    }


    /**
     * Method to generate a booking ID
     *
     * @param cineplexID The cineplex ID
     * @return The booking ID
     */
    private String genBookingID(String cineplexID) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        LocalDateTime now = LocalDateTime.now();
        String currentDateTime = dtf.format(now);
        return cineplexID + currentDateTime; // bookingID aka transaction ID
    }


    /**
     * Method to book a ticket and return a booking ID
     *
     * @param userID     The user ID
     * @param movieID    The movie ID
     * @param date       The Date
     * @param time       The time
     * @param cineplexID The cineplex ID
     * @param screenID   The screen ID
     * @param seatIDs    The array list of all seat IDs
     * @return The booking ID
     * @throws ParseException
     */
    public String BookTicket(String userID, String movieID, String date, String time, String cineplexID, String screenID, ArrayList<String> seatIDs) throws ParseException {
        String BookingID = genBookingID(cineplexID);
        BookingEY booking = new BookingEY(BookingID, userID, movieID, screenID, cineplexID, date, time, seatIDs, -1,
                this.holidayMgr, this.movieMgr, this.screenManager, this.movieGoerMgr, this.ticketPriceManager);

        // Add BookingID to corresponding user
        this.movieGoerMgr.getUserByID(userID).addBookingID(BookingID);

        // Add Bookings to Masterlist
        this.masterBookings.add(booking);

        return BookingID;
    }


    public double getTotalSales(String movieID) {
        double sales = 0;
        for (BookingEY booking: this.masterBookings) {
            if (booking.getMovieID().equals(movieID)) {
                sales += booking.getBookingAmount();
            }
        }

        return sales;
    }


    /**
     * Method to get top 5 movies based on sales
     *
     * @return Top 5 movies
     */
    public ArrayList<String> getTop5Movies() {
        Hashtable<String, Double> costDict = new Hashtable<String, Double>(); // costDict[MovieID]
        this.masterMovies.forEach((movie) -> costDict.put(movie.getMovieID(), this.getTotalSales(movie.getMovieID()))); // initialize costDict with all movieIDs and net revenue.

        Hashtable<String, Double> top5Dict = new Hashtable<String, Double>();
        double minCost;
        String minKey;
        for (String movieID : costDict.keySet()) {
            double currentCost = costDict.get(movieID); // gets current cost of movie from costDict
            if (top5Dict.size() < 5) {
                top5Dict.put(movieID, currentCost);
            } else {
                minCost = 0;
                minKey = "";
                for (String _movieID : top5Dict.keySet()) { // get minCost and Key
                    if (top5Dict.get(_movieID) <= minCost) {
                        minCost = top5Dict.get(_movieID);
                        minKey = _movieID;
                    }
                }
                if (currentCost >= minCost) {
                    top5Dict.remove(minKey);
                    top5Dict.put(movieID, currentCost);
                }
            }
        }

        SortedSet<Double> sorted =  new TreeSet<>(top5Dict.values()).descendingSet();
        ArrayList<String> top5List = new ArrayList<String>();
        int idx = 0;
        for (double revenue : sorted) {
            for (String movieID :top5Dict.keySet()) {
                if (top5Dict.get(movieID).equals(revenue)) {
                    top5List.add(movieID);
                }
            }
        }
        return top5List;
    }


    /**
     * Method to get the list of seats booked
     *
     * @param booking The Booking made
     * @return List of seats booked
     */
    public ArrayList<String> getListOfSeats(BookingEY booking) {
        ArrayList<TicketEY> tickets = booking.getTickets();
        ArrayList<String> seats = new ArrayList<String>();
        for (TicketEY ticket : tickets) {
            String seatID = ticket.getSeatId();
            seats.add(ticket.getSeatId());
        }
        return seats;
    }


    /**
     * Method to read the input data from the files
     *
     * @throws IOException
     * @throws ParseException
     */
    public void primeBookings() throws IOException, ParseException {
        String bookingSEPARATOR = "|";
        String SeatSEPARATOR = "~";
        ArrayList stringArray = null;
        String filename = this.getCentralManager().getDataFolder().concat("Bookings.txt");
        try {
            stringArray = (ArrayList) ioManager.read(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Priming of Booking objects is skipped as there is no master data");
            return;
        }

        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, bookingSEPARATOR);// pass in the string to the string
            // tokenizer using delimiter ","
            String bookingID = star.nextToken().trim(); // first token
            String userID = star.nextToken().trim();
            String movieID = star.nextToken().trim();
            String screenID = star.nextToken().trim();
            String cineplexID = star.nextToken().trim();
            String date = star.nextToken().trim();
            String time = star.nextToken().trim();
            Double price = Double.valueOf(star.nextToken().trim());
            ArrayList<String> seatIds = new ArrayList<String>();
            String seatID = null;
            String seatIdString = star.nextToken().trim();
            StringTokenizer SeatsToken = new StringTokenizer(seatIdString, SeatSEPARATOR);
            while (SeatsToken.hasMoreTokens()) {
                seatID = SeatsToken.nextToken().trim();
                seatIds.add(seatID);
            }
            BookingEY booking = new BookingEY(bookingID, userID, movieID, screenID, cineplexID, date, time, seatIds,
                    price, this.holidayMgr, this.movieMgr, this.screenManager, this.movieGoerMgr, this.ticketPriceManager);
            this.masterBookings.add(booking);
        }

    }


    /**
     * Method to write data back to the files
     *
     * @throws IOException
     */
    public void writeBookings() throws IOException {
        String bookingSEPARATOR = " | ";
        String seatSEPARATOR = " ~ ";
        String filename = this.getCentralManager().getDataFolder().concat("Bookings.txt");
        List alw = new ArrayList();
        BookingEY booking;
        for (int i = 0; i < this.masterBookings.size(); i++) {
            booking = this.masterBookings.get(i);
            StringBuilder st = new StringBuilder();
            st.append(booking.getBookingID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getUserID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getMovieID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getScreenID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getCinemaID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getDate().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getTime().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getBookingAmount());
            st.append(bookingSEPARATOR);
            for (TicketEY ticket : booking.getTickets()) {
                st.append(ticket.getSeatId());
                st.append(seatSEPARATOR);
            }
            st.append(bookingSEPARATOR);
            alw.add(st.toString());

        }
        ioManager.write(filename, alw);

    }


    public ArrayList<String> getAllBookingsList(String userID) {
        MovieGoerEY movieGoer = this.movieGoerMgr.getUserByID(userID);
        for (String b : movieGoer.getBookings()) {
            System.out.println(b);
        }
        return movieGoer.getBookings();
    }

}
