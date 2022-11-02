import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Cineplex {

	private String cineplexID;
	private String name;
	private String location;
	private Screen[] screens;
	private cineplexType type;
	private int size;

	public Cineplex() {

		this.cineplexID=UUID.randomUUID().toString();
		this.name=null;
		this.location=null;
		this.screens=new Screen[0];
		this.size=0;
	}

	/**
	 *
	 * @param name
	 * @param location
	 * @param screens
	 */
	public Cineplex(String name, String location,int totalScreens,cineplexType type) {
		
		this.cineplexID=UUID.randomUUID().toString();
		this.name=name;
		this.location=location;
		this.size=0;
		this.screens=new Screen[totalScreens];
		this.type=type;

		
	}

	public void addScreen(Screen screen) {
		this.screens[size]=screen;
		size++;
		
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

	public Screen[] getScreens() {
		return screens;
	}

	public void setScreens(Screen[] screens) {
		this.screens = screens;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int gettotalScreens() {
		return size;
		
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
				+ Arrays.toString(screens) + ", type=" + type + ", size=" + size + "]";
	}
	
	
	
	
}

