import java.text.ParseException;
import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class Holidays {

    private static String date="";
    private static ArrayList<String> datesList = new ArrayList<>();
     Scanner s=new Scanner(System.in);
    public Holidays()
    {
       //manually insert dates or initialize during runtime (decide later) testing
   
       datesList.add("2020-03-25");
       datesList.add("2019-01-27");
       datesList.add("2020-03-26");
       datesList.add("2020-02-26");
       
       System.out.println("Insert each date and press enter (q)to exit : ");
       date=s.next();// insert date format(yyyy/mm/dd)
       while(!date.equals("q"))
       {
        
       
        datesList.add(date);
        date=s.next();



       };
        
     Collections.sort(datesList);
  
       
      /*  System.out.println("Dates Object after sorting : "); testing purposes
 
       
        for (String dates : datesList) {
 
            System.out.println(dates);
        }
        */
    }

    public ArrayList<String> getHolidayList()
    {
        return datesList;

    }

    public boolean isHoliday(String date)
    {
        return datesList.contains(date);
    }

    public Boolean getWeekend(String date) throws ParseException {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date_dt = LocalDate.parse(date, dtFormatter);
        DayOfWeek day = date_dt.getDayOfWeek();
        return day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;
    }

  /*  public static void main(String[] args) testing purposes
    {
        Ratings r=new Ratings();
        System.out.println(r.getAllRatingInfo());
        System.out.println(r.getMovieName());
        System.out.println(r.getReview());
        System.out.println(r.getReviewerName());
        System.out.println(r.getRating());
    }
    */
}
