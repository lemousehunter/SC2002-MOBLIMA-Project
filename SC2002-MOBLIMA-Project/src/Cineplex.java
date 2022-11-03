import java.util.ArrayList;
import java.util.UUID;

public class Cineplex {

	private String cineplexID;
	private String name;
	private String location;
	private ArrayList<Screen> screens;
	private cineplexType type;

	public Cineplex() {

		this.cineplexID=UUID.randomUUID().toString();
		this.name=null;
		this.location=null;
		this.screens=new ArrayList<Screen>();
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
		this.screens=new ArrayList<Screen>();
		this.type=type;

		
	}

	public void addScreen(Screen screen) {
		this.screens.add(screen);
		
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

	public Screen getScreen(int index) {
		return screens.get(index);
	}

	public void setScreens(Screen screen,int index) {
		this.screens.set(index, screen);
	}

	public int getTotalScreens() {
		return screens.size();
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

	@Override
	public String toString() {
		return "Cineplex [cineplexID=" + cineplexID + ", name=" + name + ", location=" + location + ", screens="
				+ screens.toArray().toString() + ", type=" + type;
	}
	
	
	
	
}
