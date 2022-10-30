import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MoblimaApp {
    String inputFolder = System.getProperty("dataFolder");
    static ArrayList<User> userList = new ArrayList<User>();
    static ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
    ArrayList<Booking> bookings = new ArrayList<Booking>();
    static ArrayList<Movie> movies = new ArrayList<Movie>();
    ArrayList<ShowDate> showDates= new ArrayList<ShowDate>();

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
        primeMovie();
        primeShow();
        primeUser();
    }

    private static void primeUser() {
    }

    private static void primeMovie() throws IOException {
        String movieSEPARATOR = "|";
        String castSEPERATOR = "~";
        String filename = dataFolder.concat("Movies.txt");
        ArrayList stringArray = (ArrayList) read(filename);
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, movieSEPARATOR);    // pass in the string to the string tokenizer using delimiter ","
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
            ArrayList<ViewerRatings> ratings = new ArrayList<ViewerRatings>();
            Movie movie = new Movie(movieName, movieLanguage, MovieType.valueOf(movieType), MovieRating.valueOf(movieRating), ShowStatus.valueOf(showStatus), synopsis, director, castList, ratings);
            while (star.hasMoreTokens()) {
                String ratingTextAndScale = star.nextToken().trim();
                StringTokenizer ratingToken = new StringTokenizer(ratingTextAndScale, castSEPERATOR);
                String ratingText = ratingToken.nextToken().trim();
                String ratingScale = ratingToken.nextToken().trim();
                ViewerRatings rating = new ViewerRatings(ratingText, RatingScale.valueOf(ratingScale));
                ratings.add(rating);
            }
            movies.add(movie);

        }
    }
    private static void primeShow() throws IOException {
        String showSEPARATOR = "|";
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

            String  cineplexName = star.nextToken().trim();	// first token
            String  location = star.nextToken().trim();	// second token
            ArrayList<Screen> screens= new ArrayList<Screen>();
            Cineplex cineplex= new Cineplex(cineplexName,location, screens);
            while (star.hasMoreTokens()){
                String screenText= star.nextToken().trim();
                StringTokenizer screenToken= new StringTokenizer(screenText, screenSEPARATOR);
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








