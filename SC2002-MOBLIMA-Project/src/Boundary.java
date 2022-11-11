import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Boundary {
    private Input input;

    private Scanner sc;

    private CentralManagerEY centralMgr;
    private HashMap<String, Manager> managerDict;
    private HashMap<String, ArrayList> arrayDict;
    private HashMap<String, Boundary> boundaryDict;

    public Boundary() {
        this.sc = new Scanner(System.in);
        this.input = new Input(this.sc);
    }

    public Integer getInputInt(String msgToPrint) {
        return this.input.getInt(msgToPrint);
    }

    public double getInputDouble(String msgToPrint) {
        return this.input.getDouble(msgToPrint);
    }

    public String getInputLine(String msgToPrint) {
        return this.input.getLine(msgToPrint);
    }
    
    public void println(String line) {
        System.out.println(line);
    }
    
    public void print(String string) {
        System.out.println(string);
    }
    
    public void setCentralManager(CentralManagerEY CentralManager) {
        this.centralMgr = CentralManager;
    }
    
    public CentralManagerEY getCentralManager() {
        return this.centralMgr;
    }

    public ArrayList getMasterList(String arrayName) {
        return this.arrayDict.get(arrayName);
    }

    public Scanner getScanner() {
        return sc;
    }
}
