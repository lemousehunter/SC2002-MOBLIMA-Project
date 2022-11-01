import java.util.ArrayList;

public class Cineplex {

	private String name;
	private String location;
	private Screen[] screens;
	private int size;

	public Cineplex() {
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
	public Cineplex(String name, String location, ArrayList<Screen> screens) {
		
		this.name=name;
		this.location=location;
		this.size=0;
		
		for(int i=0; i<screens.size(); i++) {
			this.screens[size]=screens.get(i);
			size++;
		}
	}

	public void addScreen(Screen screen) {
		this.screens[size]=screen;
		size++;
		
	}

}
