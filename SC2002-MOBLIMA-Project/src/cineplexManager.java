import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

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
    	String cineplexID = UUID.randomUUID().toString();
        System.out.println("Enter Cineplex Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Location: ");
        String loc = sc.next();
         
        Cineplex c=new Cineplex(cineplexID, name,loc);
        masterCineplex.add(c);
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
            System.out.println(c.getName());
        }
    }

    public void searchCineplex() {
        System.out.println("Enter the cineplex name you want to search for: ");
        String user_input = sc.nextLine();
        boolean flag = false;
        for(Cineplex c:masterCineplex) {
            if(c.getName().equalsIgnoreCase(user_input)) {
                flag = true;
                break;
            }
        }
        if(flag)
            System.out.println("Cineplex Found!");
        else
            System.out.println("Cineplex not found!");
    }
	
}
