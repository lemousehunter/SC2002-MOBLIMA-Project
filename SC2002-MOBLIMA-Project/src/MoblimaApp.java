//package com.moblima.app;


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
    static ArrayList<Holidays> holidaysList= new ArrayList<Holidays>();
    static ArrayList<ViewerRatings> ratings= new ArrayList<ViewerRatings>();


    static Scanner sc = new Scanner(System.in);
    private static User sessionUser = null;
    private static String dataFolder;

    public static void main(String[] args) {
        try {
            dataFolder = System.getProperty("dataFolder");
            //prime the array lists from files
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
                    //instantiating Staff object
                    ManageStaffApp(userList);

                case 2:
                    ManageUserApp(userList);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally{
            writeDataFiles();
        }


    }

    private static void writeDataFiles() {
    }
    }

    /** Read the contents of the given file. */
    public static List read(String fileName) throws IOException {
        List data = new ArrayList() ;
        Scanner scanner = new Scanner(new FileInputStream(fileName));
        try {
            while (scanner.hasNextLine()){
                data.add(scanner.nextLine());
            }
        }
        finally{
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

    }

    private static void primeScreen() throws IOException {
        String screenSEPARATOR = "|";
        String SeatSEPARATOR="~";
        if (movies.size()==0 && cineplexes.size()==0){
            primeCineplex();
            primeMovie();
        }
        String filename = dataFolder.concat("Screens.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, screenSEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String screenID = star.nextToken().trim();    // first token
            String screenName = star.nextToken().trim();    // second token
            String screenClass = star.nextToken().trim();    // third token
            int numberOfRows = Integer.parseInt(star.nextToken().trim());    // fourth token
            int seatsPerRow = Integer.parseInt(star.nextToken().trim());    // fifth token
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<Seat> seatLayout = new ArrayList<Seat>();
            String SeatsString, SeatID, SeatRow,SeatType;
            while (star.hasMoreTokens()){
                SeatsString= star.nextToken().trim();
                StringTokenizer SeatsToken= new StringTokenizer(SeatsString, SeatSEPARATOR);
                SeatID=SeatsToken.nextToken().trim();
                SeatRow=SeatsToken.nextToken().trim();
                int SeatNumber= Integer.parseInt(SeatsToken.nextToken().trim());
                SeatType=SeatsToken.nextToken().trim();
                Seat Seat= new Seat(SeatRow,SeatNumber,SeatType);
                seatLayout.add(Seat);

            }
            Screen screen= new Screen(screenID, screenName,screenClass,numberOfRows,seatsPerRow,seatLayout);
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
            StringTokenizer star = new StringTokenizer(st, bookingSEPARATOR);// pass in the string to the string tokenizer using delimiter ","
            String viewerRatingID = star.nextToken().trim();    // first token
            String review = star.nextToken().trim();
            String rating= star.nextToken().trim();
            String userID= star.nextToken().trim();
            String movieID  = star.nextToken().trim();
            ViewerRatings rating= new ViewerRatings(viewerRatingID,review,RatingScale.valueOf(rating),userID,movieID);
            ratings.add(rating);
        }
    }

    private static void primeBookings() {
        String bookingSEPARATOR = "|";
        String filename = dataFolder.concat("Bookings.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, bookingSEPARATOR);// pass in the string to the string tokenizer using delimiter ","
            String bookingID = star.nextToken().trim();    // first token
            String userID = star.nextToken().trim();
            String movieID= star.nextToken().trim();
            String screenID= star.nextToken().trim();
            String cineplexID= star.nextToken().trim();
            String date= star.nextToken().trim();
            String time= star.nextToken().trim();
            Double price= Double.valueOf(star.nextToken().trim());
            Booking booking= new Booking(bookingID,userID,movieID,screenID,cineplexID,date,time,price);
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
            StringTokenizer star = new StringTokenizer(st, userSEPARATOR);// pass in the string to the string tokenizer using delimiter ","
            String userID = star.nextToken().trim();    // first token
            String userName = star.nextToken().trim();
            String userType = star.nextToken().trim();
            if (userType.equals(UserType.STAFF)) {
                String password = star.nextToken().trim();// first token
                Staff staff= new MovieGoer(userID,userName, userType, password);
                userList.add((User)staff);
            }
            else {
                String emailID = star.nextToken().trim();
                int  mobileNumber = Integer.parseInt(star.nextToken().trim());
                String movieGoerAge = star.nextToken().trim();
                ArrayList<String> bookings = new ArrayList<String>();
                String bookingIDString= star.nextToken().trim();
                StringTokenizer bookingToken = new StringTokenizer(bookingIDString, bookingSEPERATOR);
                while (bookingToken.hasMoreTokens()) {
                    bookings.add(bookingToken.nextToken().trim());
                }
                MovieGoer movieGoer= new MovieGoer(userID,userName, userType, emailID,mobileNumber,movieGoerAge,bookings);
                userList.add((User)movieGoer);


            }

        }
    }

    private static void primeMovie() throws IOException {
        String movieSEPARATOR = "|";
        String castSEPERATOR = "~";
        String ratingSEPERATOR="~";
        String filename = dataFolder.concat("Movies.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, movieSEPARATOR);// pass in the string to the string tokenizer using delimiter ","
            String movieID = star.nextToken().trim();    // first token
            String movieName = star.nextToken().trim();    // first token
            String movieLanguage = star.nextToken().trim();    // second token
            String movieType = star.nextToken().trim();    // third token
            String movieRating = star.nextToken().trim();    // fourth token
            String showStatus = star.nextToken().trim();    // fifth token
            String synopsis = star.nextToken().trim();    // sixth token
            String director = star.nextToken().trim();    // seventh token
            String cast = star.nextToken().trim();    // eighth token
            ArrayList<String> castList = new ArrayList<String>();
            StringTokenizer castToken = new StringTokenizer(cast, castSEPERATOR);
            while (castToken.hasMoreTokens()) {
                castList.add(castToken.nextToken().trim());
            }
            String rating= star.nextToken().trim();
            ArrayList<String> ratings = new ArrayList<String>();
            StringTokenizer ratingToken = new StringTokenizer(rating, ratingSEPERATOR);
            while (ratingToken.hasMoreTokens()) {
                ratings.add(ratingToken.nextToken().trim());
            }
            Movie movie = new Movie(movieID,movieName, movieLanguage, MovieType.valueOf(movieType), MovieRating.valueOf(movieRating), ShowStatus.valueOf(showStatus), synopsis, director, castList, ratings);
            movies.add(movie);

        }
    }
    private static void primeShow() throws IOException {
        String showSEPARATOR = "|";
        String showSeatSEPARATOR="~";
        if (movies.size()==0 && cineplexes.size()==0){
            primeCineplex();
            primeMovie();
        }
        String filename = dataFolder.concat("Shows.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, showSEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
            String showID = star.nextToken().trim();    // first token
            String showDate = star.nextToken().trim();    // second token
            String showTime = star.nextToken().trim();    // third token
            String movieID = star.nextToken().trim();    // fourth token
            String screenID = star.nextToken().trim();    // fifth token
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<ShowSeat> showSeats = new ArrayList<ShowSeat>();
            String showSeatsString, showSeatID, showSeatRow,showSeatType,occupiedString;
            boolean isOccupied;

            while (star.hasMoreTokens()){
                 showSeatsString= star.nextToken().trim();
                 StringTokenizer showSeatsToken= new StringTokenizer(showSeatsString, showSeatSEPARATOR);
                 showSeatID=showSeatsToken.nextToken().trim();
                 showSeatRow=showSeatsToken.nextToken().trim();
                 int showSeatNumber= Integer.parseInt(showSeatsToken.nextToken().trim());
                 showSeatType=showSeatsToken.nextToken().trim();
                 occupiedString=showSeatsToken.nextToken().trim();
                 if (occupiedString.equals("Y")) {
                     isOccupied=true;
                 }
                 else {
                     isOccupied=false;
                 }
                 ShowSeat showSeat= new ShowSeat(showSeatID,showSeatRow,showSeatNumber,showSeatType,isOccupied);
                showSeats.add(showSeat);

            }
            Show show= new Show(showID, showDate,showTime, movieID,screenID, emptySeats,showSeats);
            shows.add(show);
        }


    }

    private static void primeCineplex() throws IOException {
        String cineplexSEPARATOR = "|";
        String screenSEPARATOR = "%";
        String filename=dataFolder.concat("Cineplex.txt");
        ArrayList stringArray = (ArrayList)read(filename);
        for (int i = 0 ; i < stringArray.size() ; i++) {
            String st = (String)stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st , cineplexSEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
            String  cineplexID = star.nextToken().trim();	// first token
            String  cineplexName = star.nextToken().trim();	// first token
            String  location = star.nextToken().trim();	// second token
            ArrayList<Screen> screens= new ArrayList<Screen>();
            Cineplex cineplex= new Cineplex(cineplexName,location, screens);
            while (star.hasMoreTokens()){
                String screenText= star.nextToken().trim();
                StringTokenizer screenToken= new StringTokenizer(screenText, screenSEPARATOR);
                String  screenID = star.nextToken().trim();	// first token
                String screenName=screenToken.nextToken().trim();
                String screenClass= screenToken.nextToken().trim();
                int numberOfRows= Integer.parseInt(screenToken.nextToken().trim());
                int seatsPerRow= Integer.parseInt(screenToken.nextToken().trim());
                Screen screen= new Screen(screenName,ScreenClass.valueOf(screenClass),numberOfRows,seatsPerRow);
                screens.add(screen);
            }
            cineplexes.add(cineplex);
        }
    }

    private static void ManageStaffApp(ArrayList<User> userList) {
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
                    userFound=false;
                    for (int i = 0; i < userList.size(); i++) {
                        if (userList.get(i) instanceof Staff) {

                            Staff staffUser=(Staff)userList.get(i);
                            if (staffUser.getUserName().equals(userName)){
                                userFound=true;
                                if( staffUser.getPassword().equals(password)) {
                                    System.out.println("Succesfully Logged in");
                                    sessionUser = userList.get(i);
                                    break;
                                }
                                else{
                                    System.out.println("Wrong Password");
                                }
                            }
                        }
                    }
                    if ( !userFound) {
                        System.out.println("Please Enter (1) to Register");
                        break;
                    }


                case 3:
                    sessionUser=null;
            }
        } while (subchoice <= 3);
    }

    private static void ManageUserApp(ArrayList<User> userList) {
        UserApp userapp = new UserApp();

        userapp.Process();
    }

}








