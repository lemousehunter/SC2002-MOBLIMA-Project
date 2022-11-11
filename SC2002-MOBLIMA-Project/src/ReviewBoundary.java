import java.util.*;

public class ReviewBoundary{
    private Scanner s;
    private Scanner q;
    private double scale;
    private String review;
    public ReviewBoundary()
    {
      s=new Scanner(System.in);
      q=new Scanner(System.in);
      scale=1;   //dummy value
      review="";
    }

    public double setScale() {
      double scale = 0;
  
      while (scale > 5 || scale < 1) {
        System.out.println("Set rating scale (1-5)");
        try {
          scale = q.nextDouble();
        } catch (Exception e) {
          System.out.println("Enter Ratings betwen 1-5 Value only");
          q.nextLine();
        }
      }
  
      return scale;
    }
    
    public String addReview()
    {
      System.out.println("Please enter review");
      review=s.nextLine();
      return review;
    }

    public void AvgRatingPrint(double sum)
    {
      if(sum>0)
      {
        System.out.println(sum);

      }else
      {
        System.out.println("NA");
      }

    }

    public void Top5MovPrint(ArrayList<String> top5movies)
    {
      if(top5movies!=null&&top5movies.size()==5)
      {
        System.out.println("\nTop 5 movies by Average Viewer Ratings");
        System.out.println("--------------------------------------");
        for (int i=1; i<=top5movies.size();i++){
          System.out.println(i + ". " + top5movies.get(i-1));
        }
        

      }else
      {
        System.out.println("NA");
      }

    }











}