import java.util.ArrayList;

/**
 * A base interface 
 *
 */
public interface Base {
    /**
     * method to set central manager
     * @param CentralManager A central manager EY object
     */
    public void setCentralManager(CentralManagerEY CentralManager);

    /**
     * Method to get the central manager
     * @return a central manager object
     */
    public CentralManagerEY getCentralManager();

    /**
     * Method to set the Managers
     */
    public void setManagers();
}
