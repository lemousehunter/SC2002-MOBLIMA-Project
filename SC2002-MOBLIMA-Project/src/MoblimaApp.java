import java.util.*;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;

public class MoblimaApp {
    String inputFolder = System.getProperty("dataFolder");
    static ArrayList<User> userList = new ArrayList<User>();
    static ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    static ArrayList<Screen> screens = new ArrayList<Screen>();
    static ArrayList<Booking> bookings = new ArrayList<Booking>();
    static ArrayList<Show> shows = new ArrayList<Show>();
    static ArrayList<Movie> movies = new ArrayList<Movie>();
    static ArrayList<Holidays> holidaysList = new ArrayList<Holidays>();
    static ArrayList<ViewerRatings> ratings = new ArrayList<ViewerRatings>();
    static ArrayList<TicketPrice> ticketPrices = new ArrayList<TicketPrice>();

    static Scanner sc = new Scanner(System.in);
    private static User sessionUser = null;
    private static String dataFolder;

    public static void main(String[] args) throws IOException {
        try {
            dataFolder = System.getProperty("dataFolder");
            // prime the array lists from files
            primeAllObjects();
            System.out.println("==================== Welcome to Moblima Application ====================\n" +
                    " 1. Staff  Application                                             \n" +
                    " 2. User   Application                         \n" +
                    " 3. Exit                                     \n" +
                    "===========================================================");
            System.out.println("Enter choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // instantiating Staff object
                    ManageStaffApp();

                case 2:
                    ManageUserApp();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            writeDataFiles();
        }

    }

    private static void writeDataFiles() throws IOException {
        writeCineplex();
        writeScreen();
        writeMovie();
        writeShow();
        writeBookings();
        writeViewerRatings();
        writeUser();
        writeHolidays();
        writeTicketPrices();

    }

    private static void writeViewerRatings() throws IOException {
        String ratingSEPARATOR = "|";
        String filename = dataFolder.concat("Ratings.txt");
        List alw = new ArrayList();
        ViewerRatings rating;
        for (int i = 0; i < ratings.size(); i++) {
            rating = ratings.get(i);
            StringBuilder st = new StringBuilder();
            st.append(rating.getViewerRatingId().trim());
            st.append(ratingSEPARATOR);
            st.append(rating.getReview().trim());
            st.append(ratingSEPARATOR);
            st.append(rating.getRating().toString().trim());
            st.append(ratingSEPARATOR);
            st.append(rating.getUserId().trim());
            st.append(ratingSEPARATOR);
            st.append(rating.getMovieId().trim());
            alw.add(st.toString());

        }
        write(filename, alw);
    }

    private static void writeShow() {
        String showSEPARATOR = "|";
        String showSeatSEPARATOR = "~";
        String filename = dataFolder.concat("Shows.txt");
        List alw = new ArrayList();
        ArrayList<ShowSeat> showSeats;
        Show show;
        for (int i = 0; i < shows.size(); i++) {
            show = shows.get(i);
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

            showSeats = show.getShowSeatLayout();
            for (int j = 0; j < showSeats.size(); j++) {
                ShowSeat showSeat = showSeats.get(j);
                st.append(showSeat.getShowSeatID().trim());
                st.append(showSeatSEPARATOR);
                st.append(showSeat.getShowSeatRow().trim());
                st.append(showSeatSEPARATOR);
                st.append(showSeat.getShowSeatNumber());
                st.append(showSeatSEPARATOR);
                st.append(showSeat.getShowSeatType().trim());
                st.append(showSeatSEPARATOR);
                if (showSeat.isOccupied()) {
                    st.append("Y");
                } else {
                    st.append("N");
                }
            }
            alw.add(st.toString());

        }
        write(filename, alw);

    }

    private static void writeTicketPrices() throws IOException {
        String filename = dataFolder.concat("TicketPrices.txt");
        String priceSEPARATOR = "|";
        List alw = new ArrayList();
        TicketPrice ticketPrice;
        for (int i = 0; i < ticketPrices.size(); i++) {
            ticketPrice = ticketPrices.get(i);
            StringBuilder st = new StringBuilder();
            st.append(ticketPrice.getDayType().toString().trim());
            st.append(priceSEPARATOR);
            st.append(ticketPrice.getScreenClass().toString().trim());
            st.append(priceSEPARATOR);
            st.append(ticketPrice.getMovieGoerAge().toString().trim());
            st.append(priceSEPARATOR);
            st.append(ticketPrice.getMovieType().toString().trim());
            st.append(priceSEPARATOR);
            st.append(ticketPrice.getPrice());
            alw.add(st.toString());

        }
        write(filename, alw);

    }

    private static void writeHolidays() throws IOException {
        String filename = dataFolder.concat("Holidays.txt");
        List alw = new ArrayList();
        Holidays holiday;
        for (int i = 0; i < holidaysList.size(); i++) {
            holiday = holidaysList.get(i);
            StringBuilder st = new StringBuilder();
            st.append(holiday.getDate().trim());
            alw.add(st.toString());
        }
        write(filename, alw);

    }

    private static void writeUser() {
        String userSEPARATOR = "|";
        String bookingSEPARATOR = "~";
        String filename = dataFolder.concat("Users.txt");
        List alw = new ArrayList();
        ArrayList<String> bookings;
        User user;
        for (int i = 0; i < userList.size(); i++) {
            user = userList.get(i);
            StringBuilder st = new StringBuilder();
            st.append(user.getUserID().trim());
            st.append(userSEPARATOR);
            st.append(user.getUserName().trim());
            st.append(userSEPARATOR);
            String userType = user.getUserType().toString().trim();
            st.append(userType);
            st.append(userSEPARATOR);
            if (userType.equals(UserType.STAFF)) {
                Staff staff = (Staff) user;
                st.append(staff.getPassword().trim());
            } else {
                MovieGoer movieGoer = (MovieGoer) user;
                st.append(movieGoer.getEmailID().trim());
                st.append(userSEPARATOR);
                st.append(movieGoer.getMobileNumber().trim());
                st.append(userSEPARATOR);
                st.append(movieGoer.getMovieGoerAge().trim());
                st.append(userSEPARATOR);
                ArrayList<String> bookingIDs = movieGoer.getAllBookings();
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

    private static void writeBookings() throws IOException {
        String bookingSEPARATOR = "|";
        String filename = dataFolder.concat("Bookings.txt");
        List alw = new ArrayList();
        Booking booking;
        for (int i = 0; i < bookings.size(); i++) {
            booking = bookings.get(i);
            StringBuilder st = new StringBuilder();
            st.append(booking.bookingID.trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getUserID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getMovieID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getHallID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getCinemaID().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getDate().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getTime().trim());
            st.append(bookingSEPARATOR);
            st.append(booking.getPrice());
            alw.add(st.toString());

        }
        write(filename, alw);

    }

    private static void writeMovie() throws IOException {
        String movieSEPARATOR = "|";
        String castSEPARATOR = "~";
        String ratingSEPARATOR = "~";
        String filename = dataFolder.concat("Movies.txt");
        List alw = new ArrayList();
        ArrayList<String> castList;
        ArrayList<String> ratingList;

        Movie movie;
        for (int i = 0; i < movies.size(); i++) {
            movie = movies.get(i);
            StringBuilder st = new StringBuilder();
            st.append(movie.getMovieID().trim());
            st.append(movieSEPARATOR);
            st.append(movie.getName().trim());
            st.append(movieSEPARATOR);
            st.append(movie.getLanguage().trim());
            st.append(movieSEPARATOR);
            st.append(movie.getType());
            st.append(movieSEPARATOR);
            st.append(movie.getMovieRating().toString().trim());
            st.append(movieSEPARATOR);
            st.append(movie.getShowStatus().toString().trim());
            st.append(movieSEPARATOR);
            st.append(movie.getSynopsis().toString().trim());
            st.append(movieSEPARATOR);
            st.append(movie.getDirector().toString().trim());
            st.append(movieSEPARATOR);
            st.append(movie.getShowStatus().toString().trim());
            st.append(movieSEPARATOR);

            castList = movie.getCast();
            for (int j = 0; j < castList.size(); j++) {
                String cast = castList.get(j);
                st.append(cast.trim());
                st.append(castSEPARATOR);
            }
            st.append(movieSEPARATOR);
            ratingList = movie.getViewerRatingsID();
            for (int j = 0; j < ratingList.size(); j++) {
                String ratingID = ratingList.get(j);
                st.append(ratingID.trim());
                st.append(ratingSEPARATOR);
            }

            alw.add(st.toString());

        }
        write(filename, alw);
    }

    private static void writeScreen() {
        String screenSEPARATOR = "|";
        String seatSEPARATOR = "~";
        String filename = dataFolder.concat("Screens.txt");
        List alw = new ArrayList();
        ArrayList<Seat> seats;
        Screen screen;
        for (int i = 0; i < screens.size(); i++) {
            screen = screens.get(i);
            StringBuilder st = new StringBuilder();
            st.append(screen.getscreenID().trim());
            st.append(screenSEPARATOR);
            st.append(screen.getScreenName().trim());
            st.append(screenSEPARATOR);
            st.append(screen.getScreenClass().trim());
            st.append(screenSEPARATOR);
            st.append(screen.getNumberOfRows());
            st.append(screenSEPARATOR);
            st.append(screen.getSeatsPerRow());
            st.append(screenSEPARATOR);
            int emptySeats = screen.getNumberOfRows() * screen.getSeatsPerRow();
            st.append(emptySeats);
            st.append(screenSEPARATOR);

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

    private static void writeCineplex() {
        String cineplexSEPARATOR = "|";
        String screenSEPARATOR = "~";
        String filename = dataFolder.concat("Cineplex.txt");
        List alw = new ArrayList();
        ArrayList<String> screenIDs;
        Cineplex cineplex;
        for (int i = 0; i < cineplexes.size(); i++) {
            cineplex = cineplexes.get(i);
            StringBuilder st = new StringBuilder();
            st.append(cineplex.getCineplexID().trim());
            st.append(cineplexSEPARATOR);
            st.append(cineplex.getName().trim());
            st.append(cineplexSEPARATOR);
            st.append(cineplex.getLocation().trim());
            st.append(cineplexSEPARATOR);
            screenIDs = cineplex.getScreenIDs();
            for (int j = 0; j < screenIDs.size(); j++) {
                String screenID = screenIDs.get(j);
                st.append(screenID);
                st.append(screenSEPARATOR);
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

    private static void primeAllObjects() throws IOException {
        primeCineplex();
        primeScreen();
        primeMovie();
        primeShow();
        primeBookings();
        primeViewerRatings();
        primeUser();
        primeHolidays();
        primeTicketPrices();

    }

    private static void primeTicketPrices() throws IOException {
        String priceSEPARATOR = "|";
        String filename = dataFolder.concat("TicketPrices.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, priceSEPARATOR);// pass in the string to the string tokenizer
                                                                           // using delimiter ","
            String dayType = star.nextToken().trim(); // first token
            String screenClass = star.nextToken().trim();
            String moviegoerAge = star.nextToken().trim();
            String movieType = star.nextToken().trim();
            int price = Integer.parseInt(star.nextToken().trim());
            TicketPrice ticketPrice = new TicketPrice(DayType.valueOf(dayType), ScreenClass.valueOf(screenClass),
                    MovieGoerAge.valueOf(moviegoerAge), MovieType.valueOf(movieType), price);
            ticketPrices.add(ticketPrice);
        }
    }

    private static void primeHolidays() throws IOException {
        String filename = dataFolder.concat("Holidays.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st);// pass in the string to the string tokenizer using delimiter
                                                           // ","
            String holidayDate = star.nextToken().trim(); // first token
            Holidays holiday = new Holidays(holidayDate);
            holidaysList.add(holiday);
        }
    }

    private static void primeScreen() throws IOException {
        String screenSEPARATOR = "|";
        String SeatSEPARATOR = "~";
        String filename = dataFolder.concat("Screens.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, screenSEPARATOR); // pass in the string to the string
                                                                             // tokenizer using delimiter ","
            String screenID = star.nextToken().trim(); // first token
            String screenName = star.nextToken().trim(); // second token
            String screenClass = star.nextToken().trim(); // third token
            int numberOfRows = Integer.parseInt(star.nextToken().trim()); // fourth token
            int seatsPerRow = Integer.parseInt(star.nextToken().trim()); // fifth token
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<Seat> seatLayout = new ArrayList<Seat>();
            String SeatsString, SeatID, SeatRow, SeatType;
            while (star.hasMoreTokens()) {
                SeatsString = star.nextToken().trim();
                StringTokenizer SeatsToken = new StringTokenizer(SeatsString, SeatSEPARATOR);
                SeatID = SeatsToken.nextToken().trim();
                SeatRow = SeatsToken.nextToken().trim();
                int SeatNumber = Integer.parseInt(SeatsToken.nextToken().trim());
                SeatType = SeatsToken.nextToken().trim();
                Seat Seat = new Seat(SeatRow, SeatNumber, SeatType);
                seatLayout.add(Seat);

            }
            Screen screen = new Screen(screenID, screenName, screenClass, numberOfRows, seatsPerRow, seatLayout);
            screens.add(screen);
        }
    }

    private static void primeViewerRatings() throws IOException {
        String bookingSEPARATOR = "|";
        String filename = dataFolder.concat("Ratings.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, bookingSEPARATOR);// pass in the string to the string
                                                                             // tokenizer using delimiter ","
            String viewerRatingID = star.nextToken().trim(); // first token
            String review = star.nextToken().trim();
            String ratingString = star.nextToken().trim();
            String userID = star.nextToken().trim();
            String movieID = star.nextToken().trim();
            ViewerRatings rating = new ViewerRatings(viewerRatingID, userID, movieID, RatingScale.valueOf(ratingString),
                    review);
            ratings.add(rating);
        }
    }

    private static void primeBookings() throws IOException {
        String bookingSEPARATOR = "|";
        String filename = dataFolder.concat("Bookings.txt");
        ArrayList stringArray = (ArrayList) read(filename);
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
            Booking booking = new Booking(bookingID, userID, movieID, screenID, cineplexID, date, time, price);
            bookings.add(booking);
        }

    }

    private static void primeUser() throws IOException {
        String userSEPARATOR = "|";
        String bookingSEPERATOR = "~";
        String filename = dataFolder.concat("Users.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, userSEPARATOR);// pass in the string to the string tokenizer
                                                                          // using delimiter ","
            String userID = star.nextToken().trim(); // first token
            String userName = star.nextToken().trim();
            String userType = star.nextToken().trim();
            if (userType.equals(UserType.STAFF)) {
                String password = star.nextToken().trim();// first token
                Staff staff = new MovieGoer(userID, userName, userType, password);
                userList.add((User) staff);
            } else {
                String emailID = star.nextToken().trim();
                int mobileNumber = Integer.parseInt(star.nextToken().trim());
                String movieGoerAge = star.nextToken().trim();
                ArrayList<String> bookings = new ArrayList<String>();
                String bookingIDString = star.nextToken().trim();
                StringTokenizer bookingToken = new StringTokenizer(bookingIDString, bookingSEPERATOR);
                while (bookingToken.hasMoreTokens()) {
                    bookings.add(bookingToken.nextToken().trim());
                }
                MovieGoer movieGoer = new MovieGoer(userID, userName, userType, emailID, mobileNumber, movieGoerAge,
                        bookings);
                userList.add((User) movieGoer);

            }

        }
    }

    private static void primeMovie() throws IOException {
        String movieSEPARATOR = "|";
        String castSEPERATOR = "~";
        String ratingSEPERATOR = "~";
        String filename = dataFolder.concat("Movies.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, movieSEPARATOR);// pass in the string to the string tokenizer
                                                                           // using delimiter ","
            String movieID = star.nextToken().trim(); // first token
            String movieName = star.nextToken().trim(); // first token
            String movieLanguage = star.nextToken().trim(); // second token
            String movieType = star.nextToken().trim(); // third token
            String movieRating = star.nextToken().trim(); // fourth token
            String showStatus = star.nextToken().trim(); // fifth token
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
            Movie movie = new Movie(movieID, movieName, movieLanguage, MovieType.valueOf(movieType),
                    MovieRating.valueOf(movieRating), ShowStatus.valueOf(showStatus), synopsis, director, castList,
                    ratings);
            movies.add(movie);

        }
    }

    private static void primeShow() throws IOException {
        String showSEPARATOR = "|";
        String showSeatSEPARATOR = "~";
        String filename = dataFolder.concat("Shows.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, showSEPARATOR); // pass in the string to the string tokenizer
                                                                           // using delimiter ","
            String showID = star.nextToken().trim(); // first token
            String showDate = star.nextToken().trim(); // second token
            String showTime = star.nextToken().trim(); // third token
            String movieID = star.nextToken().trim(); // fourth token
            String screenID = star.nextToken().trim(); // fifth token
            int numberOfRows = Integer.parseInt(star.nextToken().trim());
            int seatsPerRow = Integer.parseInt(star.nextToken().trim());
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<ShowSeat> showSeats = new ArrayList<ShowSeat>();
            String showSeatsString, showSeatID, showSeatRow, showSeatType, occupiedString;
            boolean isOccupied;
            while (star.hasMoreTokens()) {
                showSeatsString = star.nextToken().trim();
                StringTokenizer showSeatsToken = new StringTokenizer(showSeatsString, showSeatSEPARATOR);
                showSeatID = showSeatsToken.nextToken().trim();
                showSeatRow = showSeatsToken.nextToken().trim();
                int showSeatNumber = Integer.parseInt(showSeatsToken.nextToken().trim());
                showSeatType = showSeatsToken.nextToken().trim();
                occupiedString = showSeatsToken.nextToken().trim();
                if (occupiedString.equals("Y")) {
                    isOccupied = true;
                } else {
                    isOccupied = false;
                }
                ShowSeat showSeat = new ShowSeat(showSeatID, showSeatRow, showSeatNumber, showSeatType, isOccupied);
                showSeats.add(showSeat);

            }
            Show show = new Show(showID, showDate, showTime, movieID, screenID, emptySeats, numberOfRows, seatsPerRow,
                    showSeats);
            shows.add(show);
        }

    }

    private static void primeCineplex() throws IOException {
        String cineplexSEPARATOR = "|";
        String screenSEPARATOR = "~";
        String filename = dataFolder.concat("Cineplex.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, cineplexSEPARATOR); // pass in the string to the string
                                                                               // tokenizer using delimiter ","
            String cineplexID = star.nextToken().trim(); // first token
            String cineplexName = star.nextToken().trim(); // first token
            String location = star.nextToken().trim(); // second token
            ArrayList<String> screenIDs = new ArrayList<String>();
            Cineplex cineplex = new Cineplex(cineplexID, cineplexName, location, screenIDs);
            while (star.hasMoreTokens()) {
                String screenText = star.nextToken().trim();
                StringTokenizer screenToken = new StringTokenizer(screenText, screenSEPARATOR);
                String screenID = star.nextToken().trim(); // first token
                screenIDs.add(screenID);
            }
            cineplexes.add(cineplex);
        }
    }

    private static void ManageStaffApp() {
        int subchoice;
        do {
            System.out.println("==================== Welcome to STAFF APP ====================\n" +
                    " 1. Register                                               \n" +
                    " 2. Login                                                 \n" +
                    " 3. Go Back to Main Menu                                 \n" +
                    "===========================================================");
            System.out.println("Enter choice: ");
            while (!sc.hasNextInt()) {
                System.out.println("Please enter an integer value.");
                sc.next();
            }
            subchoice = sc.nextInt();
            sc.nextLine();
            switch (subchoice) {
                case 1:
                    System.out.println("Enter UserName: ");
                    String userName = sc.nextLine();
                    System.out.println("Enter Password: ");
                    String password = sc.nextLine();
                    int userListSize;
                    if (userList.isEmpty()) {
                        userListSize = 0;
                    } else {
                        userListSize = userList.size();
                    }
                    boolean userFound = false;
                    for (int i = 0; i < userListSize; i++) {
                        if (userList.get(i).getUserName().equals(userName)) {
                            System.out.println("User Already Registered");
                            userFound = true;
                        }
                    }
                    if (!userFound) {
                        Staff staff = new Staff(UserType.STAFF, userName, password);
                        userList.add(staff);
                        System.out.println("Successfully Registered");

                    }

                case 2:
                    System.out.println("Enter UserName: ");
                    userName = sc.nextLine();
                    System.out.println("Enter Password: ");
                    password = sc.nextLine();
                    userFound = false;
                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i) instanceof Staff) {

                            Staff staffUser = (Staff) userList.get(i);
                            if (staffUser.getUserName().equals(userName)) {
                                userFound = true;
                                if (staffUser.getPassword().equals(password)) {
                                    System.out.println("Succesfully Logged in");
                                    sessionUser = userList.get(i);
                                    break;
                                } else {
                                    System.out.println("Wrong Password");
                                }
                            }
                        }
                    }
                    if (!userFound) {
                        System.out.println("Please Enter (1) to Register");
                        break;
                    }

                case 3:
                    sessionUser = null;
            }
        } while (subchoice <= 3);
    }

    private static void ManageUserApp() {
        UserApp userapp = new UserApp();
        userapp.setUserList(userList);
        userapp.setBookings(bookings);
        userapp.setCineplexes(cineplexes);
        userapp.setHolidaysList(holidaysList);
        userapp.setMovies(movies);
        userapp.setRatings(ratings);
        userapp.setScreens(screens);
        userapp.setShows(shows);

        userapp.Process();
    }

}
