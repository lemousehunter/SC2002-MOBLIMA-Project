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
        int choice = -1;
        choice = this.getInputInt("""

                        ========================= Welcome to Staff App =========================
                        1.  Add Holiday                                             \s
                        2.  List Holidays                                             \s
                        3.  Return to Staff Menu                                             \s
                        ========================================================================
                        Enter choice:\s""");
        while (choice == -1) {
            choice = this.getInputInt("Please enter an integer value. \n");
        }

        return choice;
    }

    public void printHolidayMenuChoiceError() {
        System.out.println("Enter choice betwen 1-3 values only \n");
    }

    private void holidayOperations() {
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
                    int success = this.holidayManager.addHoliday(date);
                    if (success == -1) { // duplicate error
                        this.println(date + " already exits !! \n");
                    }
                    else if (success == 0) { // parse error
                        this.println(date + " is invalid. Specify in DD-MM-YYYY format \n");
                    }
                    else { // success
                        this.println(date + " has been added \n");
                    }
                    break;
                case 2:
                    this.holidayManager.listAllHolidays();
                    break;
                case 3:
                    break;
            }
        }
    }
}
