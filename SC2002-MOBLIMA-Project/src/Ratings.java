import java.nio.channels.NonWritableChannelException;
import java.util.*;

public class Ratings {
   private String reviewerName="";
   private int rating=0;
    private String review=""; 
    private String movieName="";
    Scanner s=new Scanner(System.in);

    public Ratings()
    {

      initialize();
        
    }

    public void initialize()
    {
        System.out.println("Insert Name: ");
        reviewerName=s.nextLine();
        System.out.println("Movie Name: ");
        movieName=s.nextLine();       
        System.out.println("Insert Review: ");
        review=s.nextLine();
        System.out.println("Insert Rating: ");
        rating=s.nextInt();
        

    }
    public String getAllRatingInfo()
   {
        return "reviewer name: "+reviewerName+"\nmovie name: "+movieName+"\nrating: "+rating+"\nreview: "+review;


   }

   public String getReviewerName()
   {
     return reviewerName;
   }
   public String getMovieName()
   {
     return movieName;
   }
   public double getRating()
   {
     return rating;
   }
   public String getReview()
   {
     return review;
   }

   
   /*public static void main(String[] args) testing purposes
   {
      Holidays a=new Holidays();


      for (String date : a.getHolidayList()) {
        System.out.println(date);
      }


      System.out.println(a.isHoliday("2020-03-30"));
   }

  */

    
}
