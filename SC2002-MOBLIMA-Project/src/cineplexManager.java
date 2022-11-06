import java.util.ArrayList;
import java.util.Scanner;

public class cineplexManager {


    static Scanner sc = new Scanner(System.in);
    private ArrayList<Cineplex> masterCineplex;
   
    public void setMasterCineplex(ArrayList<Cineplex> masterCineplex) {
        this.masterCineplex = masterCineplex;
    }

    public Cineplex getCineplexByID(String cineplexID) {
        for(Cineplex c: masterCineplex) {
            if(cineplexID.equals(c.getCineplexID()))
                return c;
            else
                System.out.println("No cineplex exists with this ID!");
        }
        return null;
    }
    
    public void addCineplex() {
    	
        System.out.println("Enter Cineplex Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Location: ");
        String loc = sc.next();
        
       
        cineplexType type = null;
	      
        System.out.println("Enter cineplex type: 1 - yearRound,  2 - seasonal");
        int ch1 = sc.nextInt();
        switch(ch1)
        {
            case 1: type=cineplexType.yearRound;
            break;
            case 2: type=cineplexType.seasonal;
            break;
        }
         
        
        Cineplex c=new Cineplex(name,loc,type);
        masterCineplex.add(c);
        
 
    }

    public void updateCineplex(Cineplex c) {
       cineplexType type = null;
    	System.out.println("The current Type of the Cineplex is " + c.getType());
        System.out.println("Enter Updated Cineplex Type");
        System.out.println("Enter cineplex type: 1 - yearRound,  2 - seasonal 3- closed");
        int ch1 = sc.nextInt();
        switch(ch1)
        {
            case 1: type=cineplexType.yearRound;
            break;
            case 2: type=cineplexType.seasonal;
            break;
            case 3: type=cineplexType.closed;
            break;
        }
        
        c.setType(type);
    }

    public void removeCineplex(Cineplex c)
    {
    	c.setType(cineplexType.closed);
    	
    }
    
    public void viewCineplex() {
        for(Cineplex c: masterCineplex) {
            System.out.println(c.viewDetails());
            System.out.println("---------------------------X---------------------------");
        }
    }

    public void cineplexListing() {
        System.out.println("List of Cineplex:");
        for(Cineplex c: masterCineplex) {
            System.out.println(c.getName() + " - " + c.getType());
        }
    }

    public void searchCineplex() {
        System.out.println("Enter the cineplex name you want to search for: ");
        String user_input = sc.nextLine();
        for(Cineplex c:masterCineplex) {
            if(c.getName().equalsIgnoreCase(user_input)) {
                System.out.println("Cineplex Found!");
                System.out.println( c.getName() + " - " + c.getType());
            }
            else
                System.out.println("Cineplex not Found!");
                
        }
    }
	
}
