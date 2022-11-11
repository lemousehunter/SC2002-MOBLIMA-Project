import java.util.ArrayList;
import java.util.Scanner;


public class HolidayBoundary {

   private Scanner s;
    private Scanner q;
    private int choice;
    private String holidayDate;
  
    public HolidayBoundary() {
      s = new Scanner(System.in);
      q = new Scanner(System.in);
      choice = 0;
    }
  
    public String setHolidayDate() {
        System.out.print("Please enter Date (DD-MM-YYYY) : ");
        holidayDate=s.nextLine();
        return holidayDate;
    }

    public void printHolidayDuplicateError() {
        System.out.println( holidayDate + " already exits !! \n");

    }

    public void printAddHolidaySuccessMessaage() {
        System.out.println( holidayDate + " has been added \n");

    }

    public void printAllHolidays(ArrayList<String> masterHolidaysList) {
        System.out.println("List of Holidays configured !");
        for (String holiday : masterHolidaysList){
            System.out.println(holiday);
        }
    }

    public void printInvalidDate() {
        System.out.println( holidayDate + " is invalid. Specify in DD-MM-YYYY format \n");

    }

}
