import java.text.ParseException;
import java.util.*;
import java.io.IOException;

public class MoblimaApp {

    CentralManagerEY centralManager;

    Scanner sc = new Scanner(System.in);
    private String dataFolder;

    public MoblimaApp() {

    }

    public static void main(String[] args) throws IOException {

        MoblimaApp moblimaApp = new MoblimaApp();
        moblimaApp.process(args);
        System.out.println("Moblima Application is Terminating ...");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    System.out.println("Writing all  Master files");

                    // call all entity managers write method to write to files from the master array
                    // lists
                    moblimaApp.writeDataFiles();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void process(String[] args) throws IOException {
        try {

            // Instantiate CentralManager
            centralManager = new CentralManagerEY();

            // call all entity managers prime method to prime the array lists from files
            primeAllObjectsFromDataFiles();

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
        this.centralManager.getHolidayMgr().writeHolidays();
        this.centralManager.getCineplexMgr().writeCineplex();
        this.centralManager.getScreenMgr().writeScreen();
        this.centralManager.getMovieMgr().writeMovie();
        this.centralManager.getShowMgr().writeShow();
        this.centralManager.getBookingMgr().writeBookings();
        this.centralManager.getReviewMgr().writeViewerRatings();
        this.centralManager.getUserMgr().writeUser();
        this.centralManager.getTicketPriceMgr().writeTicketPrice();
    }

    private void primeAllObjectsFromDataFiles() throws IOException, ParseException {
        this.centralManager.getHolidayMgr().primeHolidays();
        this.centralManager.getCineplexMgr().primeCineplex();
        this.centralManager.getScreenMgr().primeScreen();
        this.centralManager.getMovieMgr().primeMovie();
        this.centralManager.getShowMgr().primeShow();
        this.centralManager.getUserMgr().primeUser();
        this.centralManager.getBookingMgr().primeBookings();
        this.centralManager.getReviewMgr().primeViewerRatings();
        this.centralManager.getTicketPriceMgr().primeTicketPrice();

    }

    private void ManageStaffApp() {
        int subchoice;
        boolean userLoggedin = false;
        do {
            System.out.println("\n========================= Welcome to Staff App =========================\n" +
                    "1. Register                                               \n" +
                    "2. Login                                                 \n" +
                    "3. Exit Moblima App                               \n" +
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
                    if (this.centralManager.getMasterUsers().isEmpty()) {
                        masterUserListSize = 0;
                    } else {
                        masterUserListSize = this.centralManager.getMasterUsers().size();
                    }
                    boolean userFound = false;
                    for (int i = 0; i < masterUserListSize; i++) {
                        if (this.centralManager.getMasterUsers().get(i).getUserName().equals(userName)) {
                            System.out.println("\n" + userName + "  Already Registered");
                            userFound = true;
                        }
                    }
                    if (!userFound) {
                        Staff staff = new Staff(userName, password);
                        this.centralManager.getMasterUsers().add(staff);
                        System.out.println("\n" + userName + " Successfully Registered");

                    }
                    break;

                case 2:
                    userLoggedin = false;
                    System.out.print("Enter UserName: ");
                    userName = sc.nextLine();
                    System.out.print("Enter Password: ");
                    password = sc.nextLine();
                    userFound = false;
                    for (int i = 0; i < this.centralManager.getMasterUsers().size(); i++) {
                        if (this.centralManager.getMasterUsers().get(i) instanceof Staff) {

                            Staff staffUser = (Staff) this.centralManager.getMasterUsers().get(i);
                            if (staffUser.getUserName().equals(userName)) {
                                userFound = true;
                                if (staffUser.getPassword().equals(password)) {
                                    System.out.println("\n" + userName + " Succesfully Logged in");
                                    userLoggedin = true;
                                    break;
                                } else {
                                    System.out.println("Invalid Password. Try Loggin in again \n");
                                }
                            }
                        }
                    }
                    if (!userFound) {
                        System.out.println("\nUser " + userName + " not registered \n");
                        System.out.println("Please Enter (1) to Register \n \n3");
                        break;
                    }
                    if (userLoggedin) {
                        this.centralManager.getStaffBoundary().staffOperations();
                        // force user app to terminate by setting subchoice to 3
                        subchoice = 3;
                    }

                    break;

                case 3: // return to mainMenu
                    break;
            }
        } while (subchoice < 3);
    }

    private void ManageMovieGoerApp() throws ParseException {
        int subchoice;
        boolean userLoggedin = false;
        MovieGoerEY movieGoer = null;
        do {
            System.out.println("\n========================= Welcome to User  App =========================\n" +
                    "1. Register                                               \n" +
                    "2. Login                                                 \n" +
                    "3. Exit Moblima App                                 \n" +
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
                    int masterUserListSize;
                    if (this.centralManager.getMasterUsers().isEmpty()) {
                        masterUserListSize = 0;
                    } else {
                        masterUserListSize = this.centralManager.getMasterUsers().size();
                    }
                    boolean userFound = false;
                    for (int i = 0; i < masterUserListSize; i++) {
                        if (this.centralManager.getMasterUsers().get(i).getUserName().equals(userName)) {
                            System.out.println("\n" + userName + "  Already Registered");
                            userFound = true;
                            break;
                        }
                    }
                    System.out.print("Enter Email ID: ");
                    String emalid = sc.nextLine();
                    System.out.print("Enter Mobile #: ");
                    String  mobileNumber = sc.nextLine();
                    System.out.print("Enter Age     : ");
                    int  age  = sc.nextInt();
                    ArrayList<String> bookings = new ArrayList<String>();

                    String userid = UUID.randomUUID().toString();
                    movieGoer = new MovieGoerEY(userid,userName,emalid,mobileNumber,age,bookings);
                    this.centralManager.getMasterUsers().add(movieGoer);
                    System.out.println("\n" + userName + " Successfully Registered");

                    break;

                case 2:
                    userLoggedin = false;
                    System.out.print("Enter UserName: ");
                    userName = sc.nextLine();
                    userFound = false;
                    for (int i = 0; i < this.centralManager.getMasterUsers().size(); i++) {
                        if (this.centralManager.getMasterUsers().get(i) instanceof MovieGoerEY) {

                            movieGoer = (MovieGoerEY) this.centralManager.getMasterUsers().get(i);
                            if (movieGoer.getUserName().equals(userName)) {
                                userFound = true;
                                userLoggedin = true;
                            }
                        }
                    }
                    if (!userFound) {
                        System.out.println("\nUser " + userName + " not registered \n");
                        System.out.println("Please Enter (1) to Register \n \n3");
                        break;
                    }
                    if (userLoggedin) {
                        this.centralManager.getMovieGoerBoundary().MovieGoerOperations(movieGoer.getUserID());
                        // force user app to terminate by setting subchoice to 3
                        subchoice = 3;
                    }

                    break;

                case 3: // return to mainMenu
                    break;
            }
        } while (subchoice < 3);
    }

}
