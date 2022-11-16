package Controller;

import Entity.CineplexEY;
import Entity.ScreenEY;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A Controller.CineplexManager object
 * <p>
 * A <code>Controller.CineplexManager</code> object contains all the parameters and methods required
 * to communicate between entity and boundary of Cineplex Class
 * </p>
 */

public class CineplexManager extends Manager implements BaseManager {

    // managers
    private IoManager ioManager;

    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterSceens;
   
    /**
     * Default Constructor
     */
    public CineplexManager() {

    }

    
    /**
     * Method to set controller class
     */
    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
    }

    /**
     * Method to set Master lists
     */
    @Override
    public void setMasterLists() {
        this.masterCineplexes = this.getCentralManager().getMasterCineplexes();
        this.masterSceens = this.getCentralManager().getMasterScreens();
    }

    
    /** 
     * Method to return cineplex by cineplexID
     * @param cineplexID The cineplex ID
     * @return Entity.CineplexEY
     */
    public CineplexEY getCineplexByID(String cineplexID) { // returns cineplex if found, else returns null
        for(CineplexEY c: this.masterCineplexes) {
            if(cineplexID.equals(c.getCineplexID()))
                return c;
        }
        return null;
    }
    
    
    /** 
     * Method to add a new cineplex
     * @param cinemaName The name of the cineplex
     * @param location The location of the cineplex
     * @return True if a cineplex was created, false if the cineplex already existed
     */
    public Boolean addCineplex(String cinemaName, String location) {
        String cineplexID = "";
        ArrayList<String> screenID = new ArrayList<String>();

        for(CineplexEY c: this.masterCineplexes) {
            if(c.getName().equals(cinemaName))
            {
                return false;
            }
        }
         
        CineplexEY c = new CineplexEY(cineplexID, cinemaName,location,screenID);
        c.setMasterScreens(this.masterSceens);
        this.masterCineplexes.add(c);
        return true;

    }
    
    
    /** 
     * Method to search for cineplex by name
     * @param name The cineplex name
     * @return cineplex object which matches the provided name, else returns null
     */
    public CineplexEY searchCineplexByName(String name) { // returns cineplex object which matches the provided name, else returns null
        for(CineplexEY c: this.masterCineplexes) {
            if(c.getName().startsWith(name))
            {
                return c;
            }
        }
        return null;
    }

    
    /** 
     * Method to return convert idx to cineplex ID
     * @param idx Unique ID
     * @return The cineplex ID
     */
    public String convertIDX2CineplexID(int idx) {
        return this.masterCineplexes.get(idx-1).getCineplexID();
    }
	
	
    /** 
     * Method to display all cineplexes
     * @return list of all the cineplexes
     */
    public ArrayList<String> listAllCineplexes() {
        int idx = 1;
        ArrayList<String> stringList = new ArrayList<String>();
        for (CineplexEY c: this.masterCineplexes) {
            stringList.add(idx + ": " + c.viewDetails());
            idx++;
        }

        return stringList;
	}

    
    /** 
     * Method to search for cineplex by name
     * @param cineplexName The cineplex name
     * @return cineplex object which matches the provided name, else returns null
     */
    public CineplexEY getCineplexByName(String cineplexName) {
        if (cineplexName.isEmpty()) { return null;}
        for(CineplexEY c: this.masterCineplexes) {
            if(cineplexName.equals(c.getName()))
                return c;
        }
        return null;
    }    
    
    /** 
     * Method to read from the input data
     * @throws IOException error
     */
    public void primeCineplex() throws IOException {
        String cineplexSEPARATOR = "|";
        String screenEPARATOR = "~";
        String filename = this.getCentralManager().getDataFolder().concat("Cineplex.txt");
        ArrayList stringArray = null;
        try {
            stringArray = (ArrayList) ioManager.read(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Priming of Cineplex objects is skipped as there is no master data");
            return;
        }
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, cineplexSEPARATOR); // pass in the string to the string
                                                                               // tokenizer using delimiter ","
            String cineplexID = star.nextToken().trim(); // first token
            String cineplexName = star.nextToken().trim(); // first token
            String location = star.nextToken().trim(); // second token
            ArrayList<String> screenIDs = new ArrayList<String>();
            String screenText = star.nextToken().trim();
            StringTokenizer screenToken = new StringTokenizer(screenText, screenEPARATOR);
            while (screenToken.hasMoreTokens()) {

                String screenID = screenToken.nextToken().trim(); // first token
                screenIDs.add(screenID);
            }
            CineplexEY cineplex = new CineplexEY(cineplexID, cineplexName, location, screenIDs);
            cineplex.setMasterScreens(masterSceens);
            this.masterCineplexes.add(cineplex);
        }
    }

    
    /** 
     * Method to write back to the data files
     * @throws IOException error
     */
    public void writeCineplex() throws IOException {
        String cineplexSEPARATOR = " | ";
        String screenEPARATOR = " ~ ";
        String filename = this.getCentralManager().getDataFolder().concat("Cineplex.txt");
        List alw = new ArrayList();
        ArrayList<String> screenIDs;
        CineplexEY cineplex;
        for (int i = 0; i < this.masterCineplexes.size(); i++) {
            cineplex = this.masterCineplexes.get(i);
            StringBuilder st = new StringBuilder();
            st.append(cineplex.getCineplexID().trim());
            st.append(cineplexSEPARATOR);
            st.append(cineplex.getName().trim());
            st.append(cineplexSEPARATOR);
            st.append(cineplex.getLocation().trim());
            st.append(cineplexSEPARATOR);
            screenIDs = cineplex.getScreenID();
            for (int j = 0; j < screenIDs.size(); j++) {
                String screenID = screenIDs.get(j);
                st.append(screenID);
                st.append(screenEPARATOR);
            }

            alw.add(st.toString());

        }
         ioManager.write(filename, alw);;
    }

    
}
