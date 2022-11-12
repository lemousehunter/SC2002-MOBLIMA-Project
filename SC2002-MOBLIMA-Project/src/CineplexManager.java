import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CineplexManager extends Manager implements BaseManager {

    // managers
    private IoManager ioManager;

    private ArrayList<CineplexEY> masterCineplexes;
    private ArrayList<ScreenEY> masterSceens;
   
    public CineplexManager() {

    }

    @Override
    public void setManagers() {
        this.ioManager = this.getCentralManager().getIoManager();
    }

    @Override
    public void setMasterLists() {
        this.masterCineplexes = this.getCentralManager().getMasterCineplexes();
        this.masterSceens = this.getCentralManager().getMasterScreens();
    }

    public CineplexEY getCineplexByID(String cineplexID) { // returns cineplex if found, else returns null
        for(CineplexEY c: this.masterCineplexes) {
            if(cineplexID.equals(c.getCineplexID()))
                return c;
        }
        return null;
    }
    
    public Boolean addCineplex(String cinemaName, String location
    ) { //
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
    
    public CineplexEY searchCineplexByName(String name) { // returns cineplex object which matches the provided name, else returns null
        for(CineplexEY c: this.masterCineplexes) {
            if(c.getName().startsWith(name))
            {
                return c;
            }
        }
        return null;
    }

    public String convertIDX2CineplexID(int idx) {
        return this.masterCineplexes.get(idx).getCineplexID();
    }
	
	public ArrayList<String> listAllCineplexes() {
        int idx = 1;
        ArrayList<String> stringList = new ArrayList<String>();
        for (CineplexEY c: this.masterCineplexes) {
            stringList.add(idx + ": " + c.viewDetails());
            idx++;
        }

        return stringList;
	}

    public CineplexEY getCineplexByName(String cineplexName) {
        if (cineplexName.isEmpty()) { return null;}
        for(CineplexEY c: this.masterCineplexes) {
            if(cineplexName.equals(c.getName()))
                return c;
        }
        return null;
    }    
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
