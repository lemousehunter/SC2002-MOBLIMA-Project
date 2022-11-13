package Boundary;

import Controller.Manager;
import Entity.CentralManagerEY;
import App.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * A Boundary.Boundary Object
 * 
 * <p>
 * A <code>Boundary.Boundary</code> object is the object of the parent class of all other boundary classes
 */
public class Boundary {
    private Input input;

    private Scanner sc;

    private CentralManagerEY centralMgr;
    private HashMap<String, Manager> managerDict;
    private HashMap<String, ArrayList> arrayDict;
    private HashMap<String, Boundary> boundaryDict;

    /**
     * Creates a new object of class Boundary.Boundary
     */
    public Boundary() {
        this.sc = new Scanner(System.in);
        this.input = new Input(this.sc);
    }

    
    /** 
     * Method to print a message and ask for user input
     * @param msgToPrint The message that needs to be printed
     * @return asks for integer input from the user
     */
    public Integer getInputInt(String msgToPrint) {
        return this.input.getInt(msgToPrint);
    }

    
    /** 
     * Method to print a message and ask for user input
     * @param msgToPrint The message that needs to be printed
     * @return asks for input needed from the user in Double
     */
    public double getInputDouble(String msgToPrint) {
        return this.input.getDouble(msgToPrint);
    }

    
    /** 
     * Method to print a message and ask for user input
     * @param msgToPrint The message that needs to be printed
     * @return asks for input needed from user in String
     */
    public String getInputLine(String msgToPrint) {
        return this.input.getLine(msgToPrint);
    }
    
    
    /** 
     * Method to print a line and move on to the next line
     * @param line The statement that needs to be printed
     */
    public void println(String line) {
        System.out.println(line);
    }
    
    
    /** 
     * Method to print a line
     * @param string The string that needs to be printed
     */
    public void print(String string) {
        System.out.println(string);
    }
    
    
    /** 
     * Method to set CentralManager
     * @param CentralManager New CentralManager object
     */
    public void setCentralManager(CentralManagerEY CentralManager) {
        this.centralMgr = CentralManager;
    }
    
    
    /** 
     * Method to get Entity.CentralManagerEY object
     * @return Object of Entity.CentralManagerEY
     */
    public CentralManagerEY getCentralManager() {
        return this.centralMgr;
    }

    /**
     * Method to return a particular master list
     * @param arrayName Master list required
     * @return Master list
     */
    public ArrayList getMasterList(String arrayName) {
        return this.arrayDict.get(arrayName);
    }

    
    /** 
     * Method to return the scanner object
     * @return Scanner object
     */
    public Scanner getScanner() {
        return sc;
    }
}
