import java.text.ParseException;
import java.util.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MoblimaApp {
    ArrayList<User> masterUserList;
    ArrayList<Cineplex> masterCineplexes;
    ArrayList<Screen> masterScreens;
    ArrayList<Booking> masterBookings;
    ArrayList<Show> masterShows;
    ArrayList<Movie> masterMovies;
    ArrayList<String> masterHolidaysList;
    ArrayList<ViewerRatings> masterRatings;
    //ArrayList<Ticket> tickets;

    // Managers
    MovieManager movieMgr;
    MovieGoerManager movieGoerMgr;
    ScreenManager screenMgr;
    CineplexManager cineplexMgr;
    HolidayManager holidayMgr;
    BookingManager bookingMgr;
    StaffManager staffManager;
    ShowManager showManager;
    MovieGoerManager movieGoerManager;
    ReviewManager reviewManager;

    SeatManager seatMgr;



    Scanner sc = new Scanner(System.in);
    private User sessionUser = null;
    private String dataFolder;

    public MoblimaApp() {
        // attributes
        this.masterUserList = new ArrayList<User>();
        this.masterCineplexes = new ArrayList<Cineplex>();
        this.masterScreens = new ArrayList<Screen>();
        this.masterBookings = new ArrayList<Booking>();
        this.masterShows = new ArrayList<Show>();
        this.masterMovies = new ArrayList<Movie>();
        this.masterHolidaysList = new ArrayList<String>();
        this.masterRatings = new ArrayList<ViewerRatings>();
        //this.tickets = new ArrayList<Ticket>();

     }

    public static void main(String[] args) throws IOException{

        MoblimaApp moblimaApp = new MoblimaApp();
        moblimaApp.process(args);
        System.out.println("Moblima Application is Terminating ...");

        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                try {
                    System.out.println("Writing all  Master files");
                    moblimaApp.writeDataFiles();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
           
    }
    public void process(String[] args) throws IOException {
        try {
            this.dataFolder = System.getProperty("dataFolder");
            if (this.dataFolder == null){
                this.dataFolder = "C:\\Users\\swami\\Downloads\\Varsha\\SC2002-MOBLIMA-Project\\database\\";
            }
            // prime the array lists from files
            primeAllObjects();
            // managers
            this.movieMgr = new MovieManager();
            this.movieMgr.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);
        
            this.movieGoerMgr = new MovieGoerManager(movieMgr, showManager, bookingMgr, reviewManager);
            this.movieGoerMgr.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);
       
            this.screenMgr = new ScreenManager();
            this.screenMgr.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);

            this.seatMgr = new SeatManager();
            this.seatMgr.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings, masterShowSeats);

            this.cineplexMgr = new CineplexManager();
            this.cineplexMgr.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);
       
            this.holidayMgr = new HolidayManager(); 
            this.holidayMgr.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);
            
            this.bookingMgr = new BookingManager(this.movieMgr, this.holidayMgr, this.seatMgr);
            this.holidayMgr.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);

            this.reviewManager = new ReviewManager();
            this.reviewManager.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings, masterShowSeats);

            this.staffManager = new StaffManager(this.cineplexMgr, this.screenMgr, this.showManager, this.movieMgr);
            this.staffManager.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);

            this.movieGoerManager = new MovieGoerManager(this.movieMgr, this.showManager, this.bookingMgr, this.reviewManager);
            this.movieGoerManager.setMasterLists(masterUserList, masterCineplexes, masterScreens, masterBookings, masterShows, masterMovies, masterHolidaysList, masterRatings);

            System.out.println("\n==================== Welcome to Moblima Application ====================\n" +
                               " 1. Staff  Application                                             \n" +
                               " 2. User   Application                         \n" +
                               " 3. Exit                                     \n" +
                               "========================================================================");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ManageStaffApp();
                    break;

                case 2:
                    ManageMovieGoerApp();
                    break;
                
                case 3:
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } 

    }

    private void writeDataFiles() throws IOException {
        this.writeCineplex();
        this.writeScreen();
        this.writeMovie();
        this.writeShow();
        this.writeBookings();
        this.writeViewerRatings();
        this.writeUser();
        this.writeHolidays();
        //this.writeTicketPrice();
    }

    private void writeViewerRatings() throws IOException {
        String ratingsEPARATOR = " | ";
        String filename = this.dataFolder.concat("Ratings.txt");
        List alw = new ArrayList();
        ViewerRatings rating;
        for (int i = 0; i < this.masterRatings.size(); i++) {
            rating = this.masterRatings.get(i);
            StringBuilder st = new StringBuilder();
            st.append(rating.getViewerRatingId().trim());
            st.append(ratingsEPARATOR);
            st.append(rating.getReview().trim());
            st.append(ratingsEPARATOR);
            st.append(String.valueOf(rating.getRating()));
            st.append(ratingsEPARATOR);
            st.append(rating.getUserId().trim());
            st.append(ratingsEPARATOR);
            st.append(rating.getMovieId().trim());
            alw.add(st.toString());

        }
        write(filename, alw);
    }

    private void writeShow() throws IOException {
        String showSEPARATOR = " | ";
        String ShowSeatSEPARATOR = " ~ ";
        String filename = this.dataFolder.concat("Shows.txt");
        List alw = new ArrayList();
        ArrayList<ShowSeat> ShowSeats;
        Show show;
        for (int i = 0; i < this.masterShows.size(); i++) {
            show = this.masterShows.get(i);
            StringBuilder st = new StringBuilder();
            st.append(show.getShowID().trim());
            st.append(showSEPARATOR);
            st.append(show.getShowDate().trim());
            st.append(showSEPARATOR);
            st.append(show.getShowTime().trim());
            st.append(showSEPARATOR);
            st.append(show.getMovieID().trim());
            st.append(showSEPARATOR);
            st.append(show.getScreenID().trim());
            st.append(showSEPARATOR);
            st.append(show.getNumberOfRows());
            st.append(showSEPARATOR);
            st.append(show.getSeatsPerRow());
            st.append(showSEPARATOR);
            st.append(show.getEmptySeats());
            st.append(showSEPARATOR);

            ShowSeats = show.getSeatLayout();
            for (int j = 0; j < ShowSeats.size(); j++) {
                ShowSeat ShowSeat = ShowSeats.get(j);
                st.append(ShowSeat.getSeatID().trim());
                st.append(ShowSeatSEPARATOR);
                st.append(ShowSeat.getSeatRow().trim());
                st.append(ShowSeatSEPARATOR);
                st.append(ShowSeat.getSeatNumber());
                st.append(ShowSeatSEPARATOR);
                st.append(ShowSeat.getSeatType().trim());
                st.append(ShowSeatSEPARATOR);
                if (ShowSeat.isOccupied()) {
                    st.append("Y");
                } else {
                    st.append("N");
                }
            }
            alw.add(st.toString());

        }
        write(filename, alw);

    }

    // private void writeTicketPrice() throws IOException {
    //     String filename = this.dataFolder.concat("TicketPrices.txt");
    //     String SEPARATOR = "|";
    //     List alw = new ArrayList();
    //     Ticket ticket;
    //     for (int i = 0; i < this.tickets.size(); i++) {
    //         ticket = this.tickets.get(i);
    //         StringBuilder st = new StringBuilder();
    //         st.append(ticket.getMovieID());
    //         st.append(SEPARATOR);
    //         st.append(ticket.getUserID());
    //         st.append(SEPARATOR);
    //         st.append(ticket.getScreenID());
    //         st.append(SEPARATOR);
    //         st.append(ticket.getDate());
    //         st.append(SEPARATOR);
    //         st.append(ticket.getPrice());
    //         st.append(SEPARATOR);
    //         alw.add(st.toString());

    //     }
    //     write(filename, alw);

    // }

    private void writeHolidays() throws IOException {
        String filename = this.dataFolder.concat("Holidays.txt");
        List alw = new ArrayList();
        HolidayManager holidayMgr;
        for (int i = 0; i < this.masterHolidaysList.size(); i++) {
            String holiday = this.masterHolidaysList.get(i);
            StringBuilder st = new StringBuilder();
            st.append(holiday.trim());
            alw.add(st.toString());
        }
        write(filename, alw);

    }

    private void writeUser() throws IOException {
        String userSEPARATOR = " | ";
        String bookingSEPARATOR = " ~ ";
        String filename = this.dataFolder.concat("Users.txt");
        List alw = new ArrayList();
        ArrayList<String> bookings;
        User user;
        for (int i = 0; i < this.masterUserList.size(); i++) {
            user = this.masterUserList.get(i);
            StringBuilder st = new StringBuilder();
            st.append(user.getUserID().trim());
            st.append(userSEPARATOR);
            st.append(user.getUserName().trim());
            st.append(userSEPARATOR);
            String userType = user.getUserType().toString().trim();
            st.append(userType);
            st.append(userSEPARATOR);
            if (userType.equals(UserType.STAFF.toString())) {
                Staff staff = (Staff) user;
                st.append(staff.getPassword().trim());
            } else {
                MovieGoer movieGoer = (MovieGoer) user;
                st.append(movieGoer.getEmailID().trim());
                st.append(userSEPARATOR);
                st.append(Integer.toString(movieGoer.getMobileNumber()));
                st.append(userSEPARATOR);
                st.append(Integer.toString(movieGoer.getAge()));
                st.append(userSEPARATOR);
                ArrayList<String> bookingIDs = movieGoer.getBookings();
                for (int j = 0; j < bookingIDs.size(); j++) {
                    String bookingID = bookingIDs.get(j);
                    st.append(bookingID);
                    st.append(bookingSEPARATOR);
                }
            }

            alw.add(st.toString());

        }
        write(filename, alw);

    }

    private void writeBookings() throws IOException {
        String bookingSEPARATOR = " | ";
        String seatSEPARATOR = " ~ ";
        String filename = this.dataFolder.concat("Bookings.txt");
        List alw = new ArrayList();
        Booking booking;
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
            for (Ticket ticket : booking.getTickets()) {
                st.append(ticket.getSeatId());
                st.append(seatSEPARATOR);
            }
            st.append(bookingSEPARATOR);
            alw.add(st.toString());

        }
        write(filename, alw);

    }

    private void writeMovie() throws IOException {
        String moviesEPARATOR = " | ";
        String castSEPARATOR = " ~ ";
        String ratingsEPARATOR = " ~ ";
        String filename = this.dataFolder.concat("Movies.txt");
        List alw = new ArrayList();
        ArrayList<String> castList;
        ArrayList<String> ratingList;

        Movie movie;
        for (int i = 0; i < this.masterMovies.size(); i++) {
            movie = this.masterMovies.get(i);
            StringBuilder st = new StringBuilder();
            st.append(movie.getMovieID().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getName().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getLanguage().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getType());
            st.append(moviesEPARATOR);
            st.append(movie.getMovieRating().toString().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getShowStatus().toString().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getSynopsis().toString().trim());
            st.append(moviesEPARATOR);
            st.append(movie.getDirector().toString().trim());
            st.append(moviesEPARATOR);

            castList = movie.getCast();
            for (int j = 0; j < castList.size(); j++) {
                String cast = castList.get(j);
                st.append(cast.trim());
                st.append(castSEPARATOR);
            }
            st.append(moviesEPARATOR);
            ratingList = movie.getViewerRatingsID();
            for (int j = 0; j < ratingList.size(); j++) {
                String ratingID = ratingList.get(j);
                st.append(ratingID.trim());
                st.append(ratingsEPARATOR);
            }

            alw.add(st.toString());

        }
        write(filename, alw);
    }

    private void writeScreen() throws IOException {
        String screenEPARATOR = " | ";
        String seatSEPARATOR = "~";
        String filename = this.dataFolder.concat("Screens.txt");
        List alw = new ArrayList();
        ArrayList<Seat> seats;
        Screen screen;
        for (int i = 0; i < this.masterScreens.size(); i++) {
            screen = this.masterScreens.get(i);
            StringBuilder st = new StringBuilder();
            st.append(screen.getScreenID().trim());
            st.append(screenEPARATOR);
            st.append(screen.getScreenName().trim());
            st.append(screenEPARATOR);
            st.append(screen.getScreenClass().toString().trim());
            st.append(screenEPARATOR);
            st.append(screen.getNumberOfRows());
            st.append(screenEPARATOR);
            st.append(screen.getSeatsPerRow());
            st.append(screenEPARATOR);
            int emptySeats = screen.getNumberOfRows() * screen.getSeatsPerRow();
            st.append(emptySeats);
            st.append(screenEPARATOR);

            seats = screen.getSeatLayout();
            for (int j = 0; j < seats.size(); j++) {
                Seat seat = seats.get(j);
                st.append(seat.getSeatID().trim());
                st.append(seatSEPARATOR);
                st.append(seat.getSeatRow().trim());
                st.append(seatSEPARATOR);
                st.append(seat.getSeatNumber());
                st.append(seatSEPARATOR);
                st.append(seat.getSeatType().trim());
                st.append(seatSEPARATOR);
            }
            alw.add(st.toString());

        }
        write(filename, alw);
    }

    private void writeCineplex() throws IOException {
        String cineplexSEPARATOR = " | ";
        String screenEPARATOR = " ~ ";
        String filename = this.dataFolder.concat("Cineplex.txt");
        List alw = new ArrayList();
        ArrayList<String> screenIDs;
        Cineplex cineplex;
        for (int i = 0; i < this.masterCineplexes.size(); i++) {
            cineplex = this.masterCineplexes.get(i);
            StringBuilder st = new StringBuilder();
            st.append(cineplex.getCineplexID().trim());
            st.append(cineplexSEPARATOR);
            st.append(cineplex.getName().trim());
            st.append(cineplexSEPARATOR);
            st.append(cineplex.getLocation().trim());
            st.append(cineplexSEPARATOR);
            screenIDs = cineplex.getScreenID();
            for (int j = 0; j < screenIDs.size(); j++) {
                String screenID = screenIDs.get(j);
                st.append(screenID);
                st.append(screenEPARATOR);
            }

            alw.add(st.toString());

        }
        write(filename, alw);
    }

    public static void write(String fileName, List data) throws IOException {

        PrintWriter out = new PrintWriter(new FileWriter(fileName));

        try {
            for (int i = 0; i < data.size(); i++) {
                out.println((String) data.get(i));
            }
        } finally {
            out.close();
        }
    }

    /** Read the contents of the given file. */
    public static List read(String fileName) throws IOException {
        List data = new ArrayList();
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()) {
                data.add(scanner.nextLine());
            }
        } finally {
            scanner.close();
        }
        return data;
    }

    private void primeAllObjects() throws IOException, ParseException {
        primeCineplex();
        primeScreen();
        primeMovie();
        primeShow();
        primeBookings();
        primeViewerRatings();
        primeUser();
        primeHolidays();
        //primeTicketPrice();

    }

    // private void primeTickets() throws IOException {
    //     String SEPARATOR = "|";
    //     String filename = this.dataFolder.concat("TicketPrices.txt");
    //     ArrayList stringArray = (ArrayList) read(filename);
    //     for (int i = 0; i < stringArray.size(); i++) {
    //         String st = (String) stringArray.get(i);
    //         // get individual 'fields' of the string separated by SEPARATOR
    //         StringTokenizer star = new StringTokenizer(st, SEPARATOR);// pass in the string to the string tokenizer
    //                                                                        // using delimiter "|"
    //         String movieID = star.nextToken().trim(); // first token
    //         String userID = star.nextToken().trim();
    //         String screenID = star.nextToken().trim();
    //         String date = star.nextToken().trim();
    //         double price = Double.parseDouble(star.nextToken().trim());
    //         Ticket ticketPrice = new Ticket(movieID, userID, screenID, date, price, this.holidays, this.movieMgr);
    //         this.tickets.add(ticketPrice);
    //     }
    // }

    private void primeHolidays() throws IOException {
        String filename = this.dataFolder.concat("Holidays.txt");
        
        ArrayList stringArray = null;
        try{
             stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
            System.out.println("Priming of Holidays objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st);// pass in the string to the string tokenizer using delimiter
                                                           // ","
            String holidayDate = star.nextToken().trim(); // first token

            this.masterHolidaysList.add(holidayDate);
        }
    }

    private  void primeScreen() throws IOException {
        String screenEPARATOR = "|";
        String SeatSEPARATOR = "~";
        String filename = this.dataFolder.concat("Screens.txt");
        ArrayList stringArray = null;
        try{
             stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
            System.out.println("Priming of Screen objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, screenEPARATOR); // pass in the string to the string
                                                                             // tokenizer using delimiter ","
            String screenID = star.nextToken().trim(); // first token
            String screenName = star.nextToken().trim(); // second token
            String screenClass = star.nextToken().trim(); // third token
            int numberOfRows = Integer.parseInt(star.nextToken().trim()); // fourth token
            int seatsPerRow = Integer.parseInt(star.nextToken().trim()); // fifth token
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<Seat> seatLayout = new ArrayList<Seat>();
            String seatsString, seatID, seatRow, seatType;
            seatsString = star.nextToken().trim();
            StringTokenizer SeatsToken = new StringTokenizer(seatsString, SeatSEPARATOR);

            while (SeatsToken.hasMoreTokens()) {
                seatID = SeatsToken.nextToken().trim();
                seatRow = SeatsToken.nextToken().trim();
                int seatNumber = Integer.parseInt(SeatsToken.nextToken().trim());
                seatType = SeatsToken.nextToken().trim();
                Seat Seat = new Seat(seatID, seatRow, seatNumber, seatType);
                seatLayout.add(Seat);

            }
            Screen screen = new Screen(screenID, screenName, screenClass, numberOfRows, seatsPerRow, seatLayout);
            this.masterScreens.add(screen);
        }
    }

    private void primeViewerRatings() throws IOException {
        String bookingSEPARATOR = "|";
        String filename = this.dataFolder.concat("Ratings.txt");
        ArrayList stringArray = null;
        try{
             stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
            System.out.println("Priming of ViewerRatings objects is skipped as there is no master data");
            return;
        }        
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, bookingSEPARATOR);// pass in the string to the string
                                                                             // tokenizer using delimiter ","
            String viewerRatingID = star.nextToken().trim(); // first token
            String review = star.nextToken().trim();
            Double scale = Double.parseDouble(star.nextToken().trim());
            String userID = star.nextToken().trim();
            String movieID = star.nextToken().trim();
            ViewerRatings rating = new ViewerRatings(viewerRatingID, userID, movieID, scale, review);
            this.masterRatings.add(rating);
        }
    }

    private void primeBookings() throws IOException, ParseException {
        String bookingSEPARATOR = "|";
        String SeatSEPARATOR = "~";
        ArrayList stringArray = null;
        String filename = this.dataFolder.concat("Bookings.txt");
        try{
            stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
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
            String seatID=null;
            String seatIdString = star.nextToken().trim();
            StringTokenizer SeatsToken = new StringTokenizer(seatIdString, SeatSEPARATOR);
            while (SeatsToken.hasMoreTokens()) {
                seatID = SeatsToken.nextToken().trim();
                seatIds.add(seatID);
            }            
            Booking booking = new Booking(bookingID, userID, movieID, screenID, cineplexID, date, time, seatIds, price, this.holidayMgr, this.movieMgr);
            this.masterBookings.add(booking);
        }

    }

    private void primeUser() throws IOException {
        String userSEPARATOR = "|";
        String bookingsEPERATOR = "~";
        String filename = this.dataFolder.concat("Users.txt");
        ArrayList stringArray = null;
        try{
             stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
            System.out.println("Priming of User objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, userSEPARATOR);// pass in the string to the string tokenizer
                                                                          // using delimiter ","
            String userID = star.nextToken().trim(); // first token
            String userName = star.nextToken().trim();
            String userType = star.nextToken().trim();
            if (userType.equals(UserType.STAFF.toString())) {
                String password = star.nextToken().trim();// first token
                Staff staff = new Staff(userID, userName, password);
                masterUserList.add((User) staff);
            } else {
                String emailID = star.nextToken().trim();
                int mobileNumber = Integer.parseInt(star.nextToken().trim());
                int  movieGoerAge = Integer.parseInt(star.nextToken().trim());
                ArrayList<String> bookings = new ArrayList<String>();
                String bookingIDString = star.nextToken().trim();
                StringTokenizer bookingToken = new StringTokenizer(bookingIDString, bookingsEPERATOR);
                while (bookingToken.hasMoreTokens()) {
                    bookings.add(bookingToken.nextToken().trim());
                }
                MovieGoer movieGoer = new MovieGoer(userID, userName, emailID, mobileNumber, movieGoerAge,
                bookings);
                this.masterUserList.add(movieGoer);

            }

        }
    }

    private void primeMovie() throws IOException {
        String moviesEPARATOR = "|";
        String castSEPERATOR = "~";
        String ratingSEPERATOR = "~";
        String filename = this.dataFolder.concat("Movies.txt");
        ArrayList stringArray = null;
        try{
             stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
            System.out.println("Priming of Movie objects is skipped as there is no master data");
            return;
        }        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, moviesEPARATOR);// pass in the string to the string tokenizer
                                                                           // using delimiter ","
            String movieID = star.nextToken().trim(); // first token
            String movieName = star.nextToken().trim(); // first token
            String movieLanguage = star.nextToken().trim(); // second token
            String movieType = star.nextToken().trim(); // third token
            String movieRating = star.nextToken().trim(); // fourth token
            String showstatus = star.nextToken().trim(); // fifth token
            String synopsis = star.nextToken().trim(); // sixth token
            String director = star.nextToken().trim(); // seventh token
            String cast = star.nextToken().trim(); // eighth token
            ArrayList<String> castList = new ArrayList<String>();
            StringTokenizer castToken = new StringTokenizer(cast, castSEPERATOR);
            while (castToken.hasMoreTokens()) {
                castList.add(castToken.nextToken().trim());
            }
            String rating = star.nextToken().trim();
            ArrayList<String> ratings = new ArrayList<String>();
            StringTokenizer ratingToken = new StringTokenizer(rating, ratingSEPERATOR);
            while (ratingToken.hasMoreTokens()) {
                ratings.add(ratingToken.nextToken().trim());
            }
            Movie movie = new Movie(movieID, movieName, movieLanguage, movieType,
                    MovieRating.valueOf(movieRating), ShowStatus.valueOf(showstatus), synopsis, director, castList,
                    ratings);
            this.masterMovies.add(movie);

        }
    }

    private void primeShow() throws IOException {
        String showSEPARATOR = "|";
        String ShowSeatSEPARATOR = "~";
        String filename = this.dataFolder.concat("Shows.txt");
        ArrayList stringArray = null;
        try{
             stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
            System.out.println("Priming of Show objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, showSEPARATOR); // pass in the string to the string tokenizer
                                                                           // using delimiter ","
            String showID = star.nextToken().trim(); // first token
            String showDate = star.nextToken().trim(); // second token
            String showTime = star.nextToken().trim(); // third token
            String movieID = star.nextToken().trim(); 
            String screenID = star.nextToken().trim(); // fifth token
            // Movie movie=null;
            // for (Movie movieIterator : masterMovies) {
            //     if (movieIterator.getMovieID().equals(movieID)) {
            //         movie = movieIterator;
            //         break;
            //     }
            // }
            // String screenID = star.nextToken().trim(); // fifth token
            // Screen screen=null;
            // for (Screen screenIterator : masterScreens) {
            //     if (screenIterator.getScreenID().equals(screenID)) {
            //         screen = screenIterator;
            //         break;
            //     }
            // }
            int numberOfRows = Integer.parseInt(star.nextToken().trim());
            int seatsPerRow = Integer.parseInt(star.nextToken().trim());
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<ShowSeat> ShowSeats = new ArrayList<ShowSeat>();
            String ShowSeatsString, ShowSeatID, ShowSeatRow, ShowSeatType, occupiedString;
            boolean isOccupied;
            ShowSeatsString = star.nextToken().trim();
            StringTokenizer ShowSeatsToken = new StringTokenizer(ShowSeatsString, ShowSeatSEPARATOR);

            while (ShowSeatsToken.hasMoreTokens()) {
                ShowSeatID = ShowSeatsToken.nextToken().trim();
                ShowSeatRow = ShowSeatsToken.nextToken().trim();
                int ShowSeatNumber = Integer.parseInt(ShowSeatsToken.nextToken().trim());
                ShowSeatType = ShowSeatsToken.nextToken().trim();
                occupiedString = ShowSeatsToken.nextToken().trim();
                if (occupiedString.equals("Y")) {
                    isOccupied = true;
                } else {
                    isOccupied = false;
                }
                ShowSeat ShowSeat = new ShowSeat(ShowSeatID, ShowSeatRow, ShowSeatNumber, ShowSeatType, isOccupied);
                ShowSeats.add(ShowSeat);

            }
            Show show = new Show(showID, movieID, screenID, showDate, showTime, emptySeats,numberOfRows, seatsPerRow, this.movieMgr , this.screenMgr);
            this.masterShows.add(show);
        }

    }

    private void primeCineplex() throws IOException {
        String cineplexSEPARATOR = "|";
        String screenEPARATOR = "~";
        String filename = this.dataFolder.concat("Cineplex.txt");
        ArrayList stringArray = null;
        try{
             stringArray = (ArrayList) read(filename);
        }
        catch (FileNotFoundException e){
            System.out.println("Priming of Cineplex objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, cineplexSEPARATOR); // pass in the string to the string
                                                                               // tokenizer using delimiter ","
            String cineplexID = star.nextToken().trim(); // first token
            String cineplexName = star.nextToken().trim(); // first token
            String location = star.nextToken().trim(); // second token
            ArrayList<String> screenIDs = new ArrayList<String>();
            String screenText = star.nextToken().trim();
            StringTokenizer screenToken = new StringTokenizer(screenText, screenEPARATOR);
            while (screenToken.hasMoreTokens()) {
                
                String screenID = screenToken.nextToken().trim(); // first token
                screenIDs.add(screenID);
            }
            Cineplex cineplex = new Cineplex(cineplexID, cineplexName, location, screenIDs);
            cineplex.setMasterScreens(masterScreens);
            this.masterCineplexes.add(cineplex);
        }
    }

    private void ManageStaffApp() {
        int subchoice;
        boolean userLoggedin=false;
        do {
            System.out.println("\n========================= Welcome to Staff App =========================\n" +
                               "1. Register                                               \n" +
                               "2. Login                                                 \n" +
                               "3. Go Back to Main Menu                                 \n" +
                               "========================================================================");
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter an integer value.");
                sc.next();
            }
            subchoice = sc.nextInt();
            sc.nextLine();
            switch (subchoice) {
                case 1:
                    System.out.print("Enter UserName: ");
                    String userName = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    int masterUserListSize;
                    if (this.masterUserList.isEmpty()) {
                        masterUserListSize = 0;
                    } else {
                        masterUserListSize = this.masterUserList.size();
                    }
                    boolean userFound = false;
                    for (int i = 0; i < masterUserListSize; i++) {
                        if (this.masterUserList.get(i).getUserName().equals(userName)) {
                            System.out.println("\n"+userName+ "  Already Registered");
                            userFound = true;
                        }
                    }
                    if (!userFound) {
                        Staff staff = new Staff(userName, password);
                        masterUserList.add(staff);
                        System.out.println("\n"+userName+" Successfully Registered");

                    }
                    break;

                case 2:
                    userLoggedin=false;
                    System.out.print("Enter UserName: ");
                    userName = sc.nextLine();
                    System.out.print("Enter Password: ");
                    password = sc.nextLine();
                    userFound = false;
                    for (int i = 0; i < this.masterUserList.size(); i++) {
                        if (this.masterUserList.get(i) instanceof Staff) {

                            Staff staffUser = (Staff) this.masterUserList.get(i);
                            if (staffUser.getUserName().equals(userName)) {
                                userFound = true;
                                if (staffUser.getPassword().equals(password)) {
                                    System.out.println("\n"+userName+" Succesfully Logged in");
                                    userLoggedin=true;
                                    this.sessionUser = this.masterUserList.get(i);
                                    break;
                                } else {
                                    System.out.println("Invalid Password. Try Loggin in again \n");
                                }
                            }
                        }
                    }
                    if (!userFound) {
                        System.out.println("\nUser "+ userName + " not registered \n");
                        System.out.println("Please Enter (1) to Register \n \n3");
                        break;
                    }
                    if (userLoggedin){
                        staffManager.staffOperations();
                        // force user app to terminate by setting subchoice to 3
                        subchoice=3;    
                    }

                    break;

                case 3: // return to mainMenu
                    this.sessionUser = null;
            }
        } while (subchoice < 3);
    }

    private void ManageMovieGoerApp() {
         movieGoerMgr.process();
    }

}
