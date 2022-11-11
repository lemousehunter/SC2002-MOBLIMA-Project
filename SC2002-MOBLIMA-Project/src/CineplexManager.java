import java.util.ArrayList;

public class CineplexManager extends Manager implements BaseManager {
    private ArrayList<CineplexEY> masterCineplexes;
    
    private CineplexBoundary cineplexIO;

   
    public CineplexManager(CineplexBoundary cineplexB) {
        cineplexIO = cineplexB;
    }

    @Override
    public void setManagers() {

    }

    @Override
    public void setMasterLists() {
        this.masterCineplexes = this.getCentralManager().getMasterCineplexes();
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
}
