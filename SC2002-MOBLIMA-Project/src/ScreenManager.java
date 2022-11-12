import java.util.ArrayList;

public class ScreenManager extends Manager implements BaseManager {
    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;

	public ScreenManager() {

    }

	@Override
	public void setManagers() {

	}

	@Override
	public void setMasterLists() {
		this.masterCineplexes = this.getCentralManager().getMasterCineplexes();
		this.masterScreens = this.getCentralManager().getMasterScreens();
	}

	public ScreenEY getScreenByID(String screenID) {
		for(ScreenEY s:this.masterScreens) {
			if(screenID.equals(s.getScreenID()))
				return s;
			else
				System.out.println("No screen exists with this ID!");
		}
		return null;
	}

	public int addScreen(String cineplexName, String screenName, String screenClass, int numRows, int seatsPerRow) { // -1 for cineplex not found, 0 for duplicate error, 1 for success
		String cineplexID = null;
		for(CineplexEY c: this.masterCineplexes) {
			if(c.getName().equals(cineplexName))
			{
				cineplexID = c.getCineplexID();
			}
		}

		if (cineplexID == null) {
			return -1; // cineplex not found
		}

		for(ScreenEY s: this.masterScreens) {
			if(s.getScreenName().equalsIgnoreCase(screenName))
			{
				return 0; // screen duplicate error
			}
		}

		ScreenEY s = new ScreenEY(cineplexID,screenName,screenClass,numRows,seatsPerRow);
		this.masterScreens.add(s);
		return 1;

	}

	// public void viewScreens() {
	//     for(Screen s: masterScreens) {
	//         s.viewDetails();
	//         System.out.println("---------------------------X---------------------------");
	//     }
	// }

	// public void screenListing() {
	//     System.out.println("List of Screens:");
	//     for(Screen s:masterScreens) {
	//         System.out.println(s.getScreenName());
	//     }
	//}
	public ArrayList<String> listAllScreens() {
		int idx = 1;
		ArrayList<String> lines = new ArrayList<String>();
		for (ScreenEY screen: this.masterScreens) {
			lines.add(idx + ": " + screen.getScreenName());
			idx++;
		}
		return lines;
	}

	public String convIDX2screenID(int idx) {
		return this.masterScreens.get(idx - 1).getScreenID();
	}

	public ScreenEY searchScreen(String screenName) { // returns screen matching specified screenName, else returns null
		for(ScreenEY s: this.masterScreens) {
			if(s.getScreenName().equalsIgnoreCase(screenName))
			{
				return s;
			}
		}
		return null;
	}
}
