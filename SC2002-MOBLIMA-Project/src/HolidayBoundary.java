import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;


public class HolidayBoundary extends Boundary implements BaseBoundary {
    private HolidayManager holidayManager;
  
    public HolidayBoundary() {
    }

    @Override
    public void setManagers() {
        this.holidayManager = this.getCentralManager().getHolidayMgr();
    }

    @Override
    public void setBoundaries() {

    }
  
    public String getHolidayDate() {
        return this.getInputLine("Please enter Date (DD-MM-YYYY) : ");
    }

    public void printAllHolidays() {
        System.out.println("List of Holidays configured !");
        for (String holiday : this.holidayManager.listAllHolidays()){
            this.println(holiday);
        }
    }

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

    public void printHolidayMenuChoiceError() {
        System.out.println("Enter choice betwen 1-3 values only \n");
    }

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
                    date = this.getScanner().nextLine();
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
