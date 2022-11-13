import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 *
 */
public class Manager
{
    private CentralManagerEY centralManagerEY;

    /**
     * 
     */
    public Manager() {

    }

    /**
     * @param CentralManager aa
     */
    public void setCentralManager(CentralManagerEY CentralManager) {
        this.centralManagerEY = CentralManager;
    }

    /**
     * @return a
     */
    public CentralManagerEY getCentralManager() {
        return this.centralManagerEY;
    }

}
