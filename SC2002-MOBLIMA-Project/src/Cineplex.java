import java.util.ArrayList;
import java.util.UUID;

public class Cineplex {

	private String cineplexID;
	private String name;
	private String location;
	private ArrayList<String> screenID;
	private cineplexType type;
	private ArrayList<Screen> masterScreens;

	public Cineplex() {

		this.cineplexID=UUID.randomUUID().toString();
		this.name=null;
		this.location=null;
		this.screenID=new ArrayList<String>();
	}

	/**
	 *
	 * @param name
	 * @param location
	 * @param screens
	 */
	public Cineplex(String name, String location,cineplexType type) {
		
		this.cineplexID=UUID.randomUUID().toString();
		this.name=name;
		this.location=location;
		this.screenID=new ArrayList<String>();
		this.type=type;
	}

	public void setMasterScreens(ArrayList<Screen> masterScreens) {
		this.masterScreens = masterScreens;
	}

	public void addScreenID(Screen screen) {
		this.screenID.add(screen.getScreenID());	
	}

	public String getCineplexID() {
		return cineplexID;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getScreenID(int index) {
		return screenID.get(index);
	}

	public void setScreens(String screenID, int index) {
		this.screenID.set(index, screenID);
	}

	public int getTotalScreens() {
		return screenID.size();
	}

	public cineplexType getType() {
		return type;
	}

	public void setType(cineplexType type) {
		this.type = type;
	}

	public void setCineplexID(String cineplexID) {
		this.cineplexID = cineplexID;
	}

	public ArrayList<String> getScreenNames() {
		ArrayList<String> screens = new ArrayList<String>();
		for(String screenid: screenID) {
			for(Screen screen: masterScreens) {
				if(screenid.equals(screen.getScreenID())) {
					screens.add(screen.getScreenName());
					break;
				}
			}
		}
		return screens;
	}

	public String viewDetails() {
		return "Cineplex cineplexID = " + cineplexID + " , name = " + name + " , location = " + location + " , screens = "
				+ getScreenNames().toArray().toString() + " , type = " + type;
	}
}
