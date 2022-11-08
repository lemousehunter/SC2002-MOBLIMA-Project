import java.util.*;

public class ReviewBoundary{

    

    private Scanner s;
    private Scanner q;
    private RatingScale rate;
    private String review;
    public ReviewBoundary()
    {
      s=new Scanner(System.in);
      q=new Scanner(System.in);
      rate=RatingScale.ONE;//dummy value
      review="";
    }

    public RatingScale setScale() 
    {
      int scale=0;      
      
      while(scale>5||scale<1)
      {

        System.out.println("Set rating scale (1-5)");
        try {scale=q.nextInt();} 
        catch (Exception e) 
        {System.out.println("Enter Integer Value only");q.nextLine();}
        
        switch(scale)
        {
          case 1: rate=RatingScale.ONE;break;
          case 2: rate=RatingScale.TWO;break;
          case 3:rate=RatingScale.THREE;break;
          case 4:rate= RatingScale.FOUR;break;
          case 5:rate= RatingScale.FIVE;break;
          default: break;
        }
        
      }
      
      return rate;
    
    }
    
    String addReview()
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
        System.out.print(top5movies);

      }else
      {
        System.out.println("NA");
      }

    }











}