package Controller;

import Entity.CentralManagerEY;
/**
* A Manager Object
* 
* <p>
* A <code>Manager</code> object as
* a parent class to all other Managers
* </p>
* 
*/

public class Manager
{
    /**
     * A private CentralManagerEY object
     */
    private CentralManagerEY centralManagerEY;

    /**
     *Constructor for manager 
     */
    public Manager() {

    }

    /**
     * Method to set central manager
     * @param CentralManager The central manager object
     */
    public void setCentralManager(CentralManagerEY CentralManager) {
        this.centralManagerEY = CentralManager;
    }

    /**
     * Method to get the central manager
     * @return The central manager object
     */
    public CentralManagerEY getCentralManager() {
        return this.centralManagerEY;
    }

}
