package Boundary;

import Controller.HolidayManager;

import java.util.ArrayList;

/**
 * The Boundary.Boundary Class of Holiday
 * 
 * A <code>Boundary.HolidayBoundary</code> object contains all the attributes
 * and methods required for the interfacing of Holidays
 */
public class HolidayBoundary extends Boundary implements BaseBoundary {
    private HolidayManager holidayManager;
  
    /**
     * Default Constructor
     */
    public HolidayBoundary() {
    }

    /**
     * Method to set holiday manager
     */
    @Override
    public void setManagers() {
        this.holidayManager = this.getCentralManager().getHolidayMgr();
    }

    /**
     * Method to set boundaries
     */
    @Override
    public void setBoundaries() {

    }
  
    
    /** 
     * Method to ask user to input holiday date
     * @return message to ask user to input date
     */
    public String getHolidayDate() {
        return this.getInputLine("Please enter Date (DD-MM-YYYY) : ");
    }

    /**
     * Method to print all holidays
     */
    public void printAllHolidays() {
        System.out.println("List of Holidays configured !");
        for (String holiday : this.holidayManager.listAllHolidays()){
            this.println(holiday);
        }
    }

    
    /** 
     * Method to display holiday menu and ask for user input for choice
     * @return choice
     */
    public int getHolidayMenuChoice() {
        return this.getInputInt(

            "\n========================= Welcome to Staff App =========================\n" +
            "1.  Add Holiday                                              \n" +
            "2.  List Holidays                                              \n" +
            "3.  Return to Staff Menu                                              \n" +
            "========================================================================\n" +
            "Enter choice: "
            );
    }

    /**
     * Method to print error message at invalid input of choice
     */
    public void printHolidayMenuChoiceError() {
        System.out.println("Enter choice betwen 1-3 values only \n");
    }

    /**
     * Method to navigate through holiday menu in STAFF app
     */
    public void holidayOperations() {
        int holidayChoice = 0;
        while (holidayChoice != 3) {
            holidayChoice = this.getHolidayMenuChoice();
            if (holidayChoice < 0 | holidayChoice > 3) {
                this.printHolidayMenuChoiceError();
                continue;
            }
            switch (holidayChoice) {
                case 1:
                    String date = this.getHolidayDate();
                    int retCode = this.holidayManager.addHoliday(date);
                    if (retCode == 1 ) { // duplicate error
                        this.println("\n"+date + " already exits !!");
                    }
                    else if (retCode == 2) { // parse error
                        this.println(date + " is invalid. Specify in DD-MM-YYYY format \n");
                    }
                    else { // success
                        this.println("\n"+date + " has been added");
                    }
                    break;
                case 2:
                    this.printAllTicketPrices(this.holidayManager.listAllHolidays());
                    break;
                case 3:
                    break;
            }
        }
    }

    
    /** 
     * Method to print all holidays
     * @param listAllHolidays Array list of all holidays
     */
    private void printAllTicketPrices(ArrayList<String> listAllHolidays) {
        if (listAllHolidays.size() > 0) {
            for (String printline : listAllHolidays) {
                this.println(printline);
            }
        } else {
            this.println("\nNo Holidays configured !");
        }
    }
}