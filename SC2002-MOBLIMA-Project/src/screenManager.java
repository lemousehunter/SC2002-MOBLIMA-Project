import java.util.ArrayList;
import java.util.Scanner;

public class screenManager {
	


	    static Scanner sc = new Scanner(System.in);
	   

	    
	    public Screen getScreenByID(String screenID, ArrayList<Screen> screen) {
	        for(Screen s:screen) {
	            if(screenID.equals(s.getScreenID()))
	                return s;
	            else
	                System.out.println("No screen exists with this ID!");
	        }
	        return null;
	    }
	    
	    public void addScreen(ArrayList<Screen> screens) {
	    	
	        System.out.println("Enter Screen Name: ");
	        String name = sc.nextLine();
	        System.out.println("Enter Number of Rows: ");
	        int noRows = sc.nextInt();
	        System.out.println("Enter Seats Per Row: ");
	        int seatsRow = sc.nextInt();
	        ScreenClass screenClass = null;
	      
	        System.out.println("Enter Screen Class: 1 - PLATINUM_MOVIE_SUITES, 2 - REGULAR_SCREEN");
	        int ch1 = sc.nextInt();
	        switch(ch1)
	        {
	            case 1: screenClass = ScreenClass.PLATINUM_MOVIE_SUITES;
	            break;
	            case 2: screenClass = ScreenClass.REGULAR_SCREEN;
	            break;
	        }
	        
	        screenType type = null;
	        System.out.println("Enter Screen Type: 1 - 2D Screen, 2 - 3D Screen, 3 - 4D Screen 4 - ImaxScreen");
	        int ch2 = sc.nextInt();
	        switch(ch2)
	        {
	            case 1: type=screenType.TwoDScreen;
	            break;
	            case 2: type=screenType.ThreeDScreen;
	            break;
	            case 3: type=screenType.FourDScreen;
	            break;
	            case 4: type=screenType.ImaxScreen;
	    	     break;
	        }
	        
	        Screen screen=new Screen(name,screenClass,noRows,seatsRow,type);
	        screens.add(screen);
	    }

	    public void updateScreen(Screen screen) {
	       screenType type = null;
	    	System.out.println("The current Type of the Screen is " + screen.getScreenType());
	        System.out.println("Enter Updated Screen Type");
	        System.out.println("Enter Screen Type: 1 - 2D Screen, 2 - 3D Screen, 3 - 4D Screen 4 - ImaxScreen");
	        int ch = sc.nextInt();
	        switch(ch)
	        {
	            case 1: type=screenType.TwoDScreen;
	            break;
	            case 2: type=screenType.ThreeDScreen;
	            break;
	            case 3: type=screenType.FourDScreen;
	            break;
	            case 4: type=screenType.ImaxScreen;
	    	     break;
	        }
	        
	        screen.setScreenType(type);
	    }

	    public void removeScreen(Screen screen)
	    {
	       screen.setScreenType(screenType.Removed);
	    }
	    
	    public void viewScreens(ArrayList<Screen> screen) {
	        for(Screen s: screen) {
	            s.toString();
	            System.out.println("---------------------------X---------------------------");
	        }
	    }

	    public void screenListing(ArrayList<Screen> screen) {
	        System.out.println("List of Screens:");
	        for(Screen s:screen) {
	            System.out.println(s.getScreenName() + " - " + s.getScreenType());
	        }
	    }

	    public void searchScreen(ArrayList<Screen> screen) {
	        System.out.println("Enter the screen name you want to search for: ");
	        String user_input = sc.nextLine();
	        for(Screen s:screen) {
	            if(s.getScreenName().equalsIgnoreCase(user_input)) {
	                System.out.println("Screen Found!");
	                System.out.println(s.getScreenName() + " - " + s.getScreenType());
	            }
	            else
	                System.out.println("Screen not Found!");
	                
	        }
	    }
	}
