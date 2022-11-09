import java.util.ArrayList;

/**
 * A Cineplex Object
 * 
 * <p>
 * A <code>Cineplex</code> object used to store all the parameters
 * about a cineplex
 * </p>
 * 
 */

public class Cineplex {

	private String cineplexID;
	private String name;
	private String location;
	private ArrayList<String> screenID;
	private ArrayList<Screen> masterScreens;
	
	/**
	 * Cineplex Constructor
	 * 
	 * @param cineplexID The unique ID of a cineplex
	 * @param name The cineplex name
	 * @param location The location of the cineplex
	 */
	public Cineplex(String cineplexID, String name, String location, ArrayList<String>  screenID) {
		
		this.cineplexID=cineplexID;
		this.name=name;
		this.location=location;
		this.screenID = screenID;
	}

	
	public Cineplex(String cineplexID, String name, String loc) {
		this.cineplexID=cineplexID;
		this.name=name;
		this.location=loc;
		this.screenID = new ArrayList<String>();
	}



	/** 
	 * Set method for storing a reference to the master arraylist of Screens
	 * 
	 * @param masterScreens The master arraylist of all the screens
	 */
	public void setMasterScreens(ArrayList<Screen> masterScreens) {
		this.masterScreens = masterScreens;
	}

	
	/** 
	 * Method to update the screenIDs array with the screenID of the passed object <em>screen</em>
	 * 
	 * @param screen An object of class Screen
	 */
	public void addScreenID(Screen screen) {
		this.screenID.add(screen.getScreenID());
		masterScreens.add(screen);
	}

	
	/** 
	 * Get method for returning the Cineplex ID
	 * 
	 * @return The Cineplex ID
	 */
	public String getCineplexID() {
		return cineplexID;
	}

	
	/** 
	 * Set method for setting the Cineplex ID
	 * 
	 * @param cineplexID The Cineplex ID
	 */
	public void setCineplexID(String cineplexID) {
		this.cineplexID = cineplexID;
	}

	
	/** 
	 * Get method for returning the name of the cineplex
	 * 
	 * @return The name of the Cineplex
	 */
	public String getName() {
		return name;
	}

	
	/** 
	 * Set method for setting the name of the cineplex
	 * 
	 * @param name The name of the Cineplex
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/** 
	 * Get method for returning the location of the cineplex
	 * 
	 * @return the location of the cineplex
	 */
	public String getLocation() {
		return location;
	}

	
	/** 
	 * Set method for setting the location of the cineplex
	 * 
	 * @param location The location of the cineplex
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	
	/** 
	 * Get method for returning the screen ID
	 * 
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getScreenID() {
		return screenID;
	}

	
	/** 
	 * Set method for setting the screenID
	 * 
	 * @param screenID An arraylist of Screen IDs
	 */
	public void setScreenID(ArrayList<String> screenID) {
		this.screenID = screenID;
	}

	
	/** 
	 * Method for returning the arraylist of all the screens in the current cineplex
	 * 
	 * @return The arraylist of all the screens in the current cineplex
	 */
	public ArrayList<String> getScreenNames() {
		ArrayList<String> screenNames = new ArrayList<String>();
		for(String screenid: screenID) {
			for(Screen screen: masterScreens) {
				if(screenid.equals(screen.getScreenID())) {
					screenNames.add(screen.getScreenName());
					break;
				}
			}
		}
		return screenNames;
	}

	
	/** 
	 * Method for returning the details of the cineplex for printing on the screen
	 * 
	 * @return All the attributes of the cineplex class in a string format
	 */
	public String viewDetails() {
		return "Cineplex cineplexID = " + cineplexID + " , name = " + name + " , location = " + location + " , screens = "
				+ getScreenNames().toArray().toString();
	}
}
