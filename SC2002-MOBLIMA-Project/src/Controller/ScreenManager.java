package Controller;

import Entity.CineplexEY;
import Entity.ScreenEY;
import Entity.SeatEY;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A ScreenManager object
 * <p>
 * A <code>ScreenManager</code> object contains all the parameters and methods required
 * to communicate between entity and boundary of Screen Class
 * </p>
 */

public class ScreenManager extends Manager implements BaseManager {
    // managers
    private IoManager ioManager;

    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterScreens;
    private CineplexManager cineplexManager;

	/**
	 * Constructor for screen manager
	 */
	public ScreenManager() {

    }

	/**
	 *{@inheritDoc}
	 */
	@Override
	public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
        this.cineplexManager = this.getCentralManager().getCineplexMgr();
	}

	/**
	 *{@inheritDoc}
	 */
	@Override
	public void setMasterLists() {
		this.masterCineplexes = this.getCentralManager().getMasterCineplexes();
		this.masterScreens = this.getCentralManager().getMasterScreens();
	}

	/**
	 * Method to get screenID
	 * @param screenID The screenID
	 * @return The screen object
	 */
	public ScreenEY getScreenByID(String screenID) {
		for(ScreenEY s:this.masterScreens) {
			if(screenID.equals(s.getScreenID())){
				return s;
            }
		}
        System.out.println("No screen exists with this ID!");
		return null;
	}

    /**
     * Method to get screen name by screenID
     * @param screenID The screenID
     * @return The screen name
     */
    public String getScreenNameByID(String screenID) {
		for(ScreenEY s:this.masterScreens) {
			if(screenID.equals(s.getScreenID())){
				return s.getScreenName();
            }
		}
        System.out.println("No screen exists with this ID!");
		return null;
	}

	/**
	 * Method to add screen
	 * @param cineplexName The cineplex name
	 * @param screenName The screen name
	 * @param screenClass the screen class
	 * @param numRows The number of rows
	 * @param seatsPerRow The number of seats per row
	 * @return numeric value(0:duplicate screen,1: add screen successful,-1: cineplexID not found)
	 */
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
        this.cineplexManager.getCineplexByID(cineplexID).addScreen(s);
		return 1;

	}

	/**
	 * Method to list all screens
	 * @return The list of screen name
	 */
	public ArrayList<String> listAllScreens() {
		int idx = 1;
		ArrayList<String> lines = new ArrayList<String>();
		for (ScreenEY screen: this.masterScreens) {
			lines.add(idx + ": " + screen.getScreenName());
			idx++;
		}
		return lines;
	}

	/**
	 * Method to convert index to screenID
	 * @param idx An index
	 * @return screenID
	 */
	public String convIDX2screenID(int idx) {
		return this.masterScreens.get(idx - 1).getScreenID();
	}

	/**
	 * Method to search screen
	 * @param screenName The screen name
	 * @return The screen object
	 */
	public ScreenEY searchScreen(String screenName) { // returns screen matching specified screenName, else returns null
		for(ScreenEY s: this.masterScreens) {
			if(s.getScreenName().equalsIgnoreCase(screenName))
			{
				return s;
			}
		}
		return null;
	}

    /**
     * Method to get cineplex screen by name
     * @param cineplex The cineplex object
     * @param screenName The screen name
     * @return The ScreenEY object
     */
    public ScreenEY getCineplexScreenByName(CineplexEY cineplex, String screenName) {
        if (screenName.isEmpty()) { return null;}
        for(ScreenEY s: this.masterScreens) {
            if (s.getScreenName().equalsIgnoreCase(screenName) && ( cineplex != null && cineplex.getScreenID().contains(s.getScreenID()))){
                return s;
            }

        }        
        return null;
    }

	/**
	 * Method to read screen information from text file
	 * @throws IOException If there's read error
	 */
	public void primeScreen() throws IOException {
        String screenEPARATOR = "|";
        String SeatSEPARATOR = "~";
        String filename = this.getCentralManager().getDataFolder().concat("Screens.txt");
        ArrayList stringArray = null;
        try {
            stringArray = (ArrayList) ioManager.read(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Priming of Screen objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, screenEPARATOR); // pass in the string to the string
                                                                            // tokenizer using delimiter ","
            String screenID = star.nextToken().trim(); // first token
            String screenName = star.nextToken().trim(); // second token
            String screenClass = star.nextToken().trim(); // third token
            int numberOfRows = Integer.parseInt(star.nextToken().trim()); // fourth token
            int seatsPerRow = Integer.parseInt(star.nextToken().trim()); // fifth token
            int emptySeats = Integer.parseInt(star.nextToken().trim());// sixth token
            ArrayList<SeatEY> seatLayout = new ArrayList<SeatEY>();
            String seatsString, seatID, seatRow, seatType;
            seatsString = star.nextToken().trim();
            StringTokenizer SeatsToken = new StringTokenizer(seatsString, SeatSEPARATOR);

            while (SeatsToken.hasMoreTokens()) {
                seatID = SeatsToken.nextToken().trim();
                seatRow = SeatsToken.nextToken().trim();
                int seatNumber = Integer.parseInt(SeatsToken.nextToken().trim());
                seatType = SeatsToken.nextToken().trim();
                SeatEY Seat = new SeatEY(seatID, seatRow, seatNumber, seatType);
                seatLayout.add(Seat);

            }
            ScreenEY screen = new ScreenEY(screenID, screenName, screenClass, numberOfRows, seatsPerRow, seatLayout);
            this.masterScreens.add(screen);
        }
    }
	/**
	 * Method to write screen information to text file
	 * @throws IOException If there's read error
	 */
	public void writeScreen() throws IOException {
        String screenEPARATOR = " | ";
        String seatSEPARATOR = "~";
        String filename = this.getCentralManager().getDataFolder().concat("Screens.txt");
        List alw = new ArrayList();
        ArrayList<SeatEY> seats;
        ScreenEY screen;
        for (int i = 0; i < this.masterScreens.size(); i++) {
            screen = this.masterScreens.get(i);
            StringBuilder st = new StringBuilder();
            st.append(screen.getScreenID().trim());
            st.append(screenEPARATOR);
            st.append(screen.getScreenName().trim());
            st.append(screenEPARATOR);
            st.append(screen.getScreenClass().toString().trim());
            st.append(screenEPARATOR);
            st.append(screen.getNumberOfRows());
            st.append(screenEPARATOR);
            st.append(screen.getSeatsPerRow());
            st.append(screenEPARATOR);
            int emptySeats = screen.getNumberOfRows() * screen.getSeatsPerRow();
            st.append(emptySeats);
            st.append(screenEPARATOR);

            seats = screen.getSeatLayout();
            for (int j = 0; j < seats.size(); j++) {
                SeatEY seat = seats.get(j);
                st.append(seat.getSeatID().trim());
                st.append(seatSEPARATOR);
                st.append(seat.getSeatRow().trim());
                st.append(seatSEPARATOR);
                st.append(seat.getSeatNumber());
                st.append(seatSEPARATOR);
                st.append(seat.getSeatType().trim());
                st.append(seatSEPARATOR);
            }
            alw.add(st.toString());

        }
         ioManager.write(filename, alw);;
    }

}
