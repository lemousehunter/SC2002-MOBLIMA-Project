import java.util.ArrayList;
import java.util.Scanner;

public class ScreenBoundary {
    private Scanner s;
    private Scanner q;
    private String cineplexName;
    private String screenName;
    private String screenClass;
    private int numberOfRows;
	private int seatsPerRow;

    public ScreenBoundary() {
        s = new Scanner(System.in);
        q = new Scanner(System.in);
      }
    

    public String setCineplex() {
        System.out.print("Please enter Cineplex Name: ");
        cineplexName=s.nextLine();
        return cineplexName;
      }
    

    public String setScreen() {
        System.out.print("Please enter Screen Name: ");
        screenName=s.nextLine();
        return screenName;    
    }

    public String setScreenClass() {
        System.out.print("Please enter ScreenClass (R for REGULAR_SCREEN, P for PLATINUM_MOVIE_SUITES): ");
        screenClass=s.nextLine();
        if (screenClass.equals("R")) {
            screenClass = "REGULAR_SCREEN";
        }
        else  if (screenClass.equals("P")) {
            screenClass = "PLATINUM_MOVIE_SUITES";
        }
        return screenClass;   
     }
    
    public int setNumberOfRows() {
        System.out.print("Please enter Number of Rows: ");
        while (!s.hasNextInt()) {
            System.out.println("Please enter an integer value. ");
            s.next();
          }
        numberOfRows=s.nextInt();
        return numberOfRows;    
    }    

    public int setSeatPerRow() {
        System.out.print("Please enter Seats Per Row: ");
        while (!s.hasNextInt()) {
            System.out.println("Please enter an integer value. ");
            s.next();
          }
        seatsPerRow=s.nextInt();
        return seatsPerRow;    
    }

    public void printAllScreens(ArrayList<Screen> masterScreens) {
        System.out.println("\n---------------------------X---------------------------\n");
        System.out.println("\nScreen List :  \n ");
        for(Screen s: masterScreens) {
              System.out.println(s.viewDetails());
        }
        System.out.println("\n---------------------------X---------------------------\n");
      
    }

     public void printAddScreenSuccessMessaage() {
        System.out.println("\nScreen " + screenName + " has been added to " + cineplexName+ "!\n");

    }


    public void printScreenDuplicateError() {
        System.out.println("\n" + screenName + " already exits  \n");

    }


    public void printCinemaNotFoundMessaage() {
        System.out.println("\nScreen " + screenName + " not found! in Cineplex " + cineplexName+"\n");

    }


    public void printCineplexNotFound() {
        System.out.println("\nCineplex " + cineplexName + " is invalid \n");
    }


    public void printScreenFoundMessaage(Screen matchingScreen) {
        System.out.println("\nScreen " + String.format("| %-20s",matchingScreen.getScreenName())  + String.format("| %-22s |",matchingScreen.getScreenClass().toString()) + " #Rows = " + Integer.toString(matchingScreen.getNumberOfRows()) + " ; #Seats per Row = " + Integer.toString(matchingScreen.getSeatsPerRow()) );

    }

}
