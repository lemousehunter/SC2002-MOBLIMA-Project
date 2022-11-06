import java.util.ArrayList;
import java.util.Scanner;

public class screenManager {
		private ArrayList<Screen> masterScreens;
		static Scanner sc = new Scanner(System.in);
	       
		public void setMasterScreens(ArrayList<Screen> masterScreens) {
			this.masterScreens = masterScreens;
		}

	    public Screen getScreenByID(String screenID) {
	        for(Screen s:this.masterScreens) {
	            if(screenID.equals(s.getScreenID()))
	                return s;
	            else
	                System.out.println("No screen exists with this ID!");
	        }
	        return null;
	    }
	    
	    public void addScreen() {
	    	
	        System.out.println("Enter Screen Name: ");
	        String name = sc.nextLine();
	        System.out.println("Enter Number of Rows: ");
	        int numRows = sc.nextInt();
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

			assert screenClass != null;
			Screen screen=new Screen(name,screenClass.name(),numRows,seatsRow);
	        masterScreens.add(screen);
	    }
	    
	    public void viewScreens() {
	        for(Screen s: masterScreens) {
	            s.viewDetails();
	            System.out.println("---------------------------X---------------------------");
	        }
	    }

	    public void screenListing() {
	        System.out.println("List of Screens:");
	        for(Screen s:masterScreens) {
	            System.out.println(s.getScreenName());
	        }
	    }

	    public void searchScreen() {
	        System.out.println("Enter the screen name you want to search for: ");
	        String user_input = sc.nextLine();
	        for(Screen s:masterScreens) {
	            if(s.getScreenName().equalsIgnoreCase(user_input)) {
	                System.out.println("Screen Found!");
	                System.out.println(s.getScreenName());
	            }
	            else
	                System.out.println("Screen not Found!");    
	        }
	    }
	}
