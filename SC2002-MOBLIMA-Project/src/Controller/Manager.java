package Controller;

import Entity.CentralManagerEY;

public class Manager
{
    private CentralManagerEY centralManagerEY;

    public Manager() {

    }

    public void setCentralManager(CentralManagerEY CentralManager) {
        this.centralManagerEY = CentralManager;
    }

    public CentralManagerEY getCentralManager() {
        return this.centralManagerEY;
    }

}
