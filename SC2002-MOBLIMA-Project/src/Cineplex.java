import java.util.ArrayList;

public class Cineplex {

	private String cineplexID;
	private String name;
	private String location;
	private ArrayList<String> screenID;
	private ArrayList<Screen> masterScreens;

	/**
	 * @param cineplexID
	 * @param name
	 * @param location
	 */
	public Cineplex(String cineplexID, String name, String location) {
		
		this.cineplexID=cineplexID;
		this.name=name;
		this.location=location;
		this.screenID=new ArrayList<String>();
	}

	public void setMasterScreens(ArrayList<Screen> masterScreens) {
		this.masterScreens = masterScreens;
	}

	public void addScreenID(Screen screen) {
		this.screenID.add(screen.getScreenID());
		masterScreens.add(screen);
	}

	public String getCineplexID() {
		return cineplexID;
	}

	public void setCineplexID(String cineplexID) {
		this.cineplexID = cineplexID;
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

	public ArrayList<String> getScreenID() {
		return screenID;
	}

	public void setScreenID(ArrayList<String> screenID) {
		this.screenID = screenID;
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
				+ getScreenNames().toArray().toString();
	}
}
