
import java.util.*;


public class ReviewMgr {
    private String review;
    private RatingScale rating;
    private ViewerRatings reviewer;
    private HashMap<String,ArrayList< ViewerRatings>>reviewMap=new HashMap<String,ArrayList< ViewerRatings>>();
    private static ArrayList<ViewerRatings> ListOfViewerRatingObj=new ArrayList<ViewerRatings>(); //changed access modifier because the record will be maintained on the Master Array  
    private Scanner s=new Scanner(System.in);
    private Scanner q=new Scanner(System.in);
     
   
   public ReviewMgr() // constructor for movie goer to instantiate review manager controller to add viewer object 
   {                  // without creating the manager object multiple times

   }
      
  public ReviewMgr(ArrayList<ViewerRatings> ratings) {
  
    ListOfViewerRatingObj.addAll(ratings);
    
   }
    

   public void addReview(String userID, String movieID)
    {
      review=setReview();
      rating=setScale();
      reviewer=new ViewerRatings(userID, movieID, rating,review);       
      ListOfViewerRatingObj.add(reviewer);
     
    }   

    private void buildHashMap(ArrayList<ViewerRatings> pass)
  {
   
    for(int i=0;i<pass.size();i++)
    {
      if(reviewMap.containsKey(pass.get(i).getMovieId()))
    {
        reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));

    }else{

      reviewMap.put(pass.get(i).getMovieId(), new ArrayList<ViewerRatings>());
      reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));
    }

    }
    
}
   
    private RatingScale setScale() {
      int scale=0;
    
       RatingScale rate=RatingScale.ONE;
			while(scale>5||scale<1)
			{

				System.out.println("Set rating scale (1-5)");
      try {scale=q.nextInt();} 
        catch (Exception e) {System.out.println("Enter Integer Value only");q.nextLine();}
        
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
  private String setReview() 
  {
    String review;
    System.out.println("Please enter review");
    review=s.nextLine();
    return review;
    
  }
   
  public  ArrayList<String> top5Movies()
  { 
    
    HashMap <String,Double>avgRatingOfMovies=new HashMap<String,Double>();
    ArrayList<String> top5mv=new ArrayList<String>();
    double max=0.00;    
    double avgRating=0;

          buildHashMap(ListOfViewerRatingObj);
          for (String key: reviewMap.keySet()) 
          {                 
                avgRating=getAvgRating(key);
                avgRatingOfMovies.put(key,avgRating); 
          }

          
            //System.out.println(avgRatingOfMovies);

            for(int i=0;i<5;i++)
            { 
            try { max=(Collections.max(avgRatingOfMovies.values()));} 
            catch (Exception e) {  return top5mv; }
               
                  
              for (String key :avgRatingOfMovies.keySet()) 
              { 
                   if (avgRatingOfMovies.get(key) == max) 
                   { 
                      avgRatingOfMovies.put(key,-1.0);
                      top5mv.add(key);
                   }
              }
            }        
  

          return top5mv;
           
   }
    
   public ArrayList<ViewerRatings> getListByMovieID(String movieId)
   {
    ArrayList<ViewerRatings> reviewByMovies=new ArrayList<ViewerRatings>();
      
    for (ViewerRatings temp :ListOfViewerRatingObj) {
      if(temp.getMovieId().equals(movieId))
      {
          reviewByMovies.add(temp);

      }
      
    }

      return reviewByMovies;


   }
    

   public ArrayList<ViewerRatings> getListByUserID(String userId)
   {
    ArrayList<ViewerRatings> reviewByUser=new ArrayList<ViewerRatings>();

      for (ViewerRatings temp :ListOfViewerRatingObj) {
        if(temp.getUserId().equals(userId))
        {
            reviewByUser.add(temp);

        }
        
      }

      return reviewByUser;


   } 

   public double getAvgRating(String movieId)
   {
     double sum=0;
     int i=0;
     ArrayList<ViewerRatings>temp=getListByMovieID(movieId);
     for (i=0; i<temp.size();i++) {
       sum+=getRating(temp.get(i).getRating()) ;
    }

      sum=sum/i;
      sum = sum*100;
      sum = Math.round(sum);
     sum = sum /100;
     return sum;

   }
   public String getViewerRatingID(String userId)
   {
   
     for (ViewerRatings temp:ListOfViewerRatingObj) {
       if(temp.getUserId().equals(userId))
       {
          return temp.getUserId();

       }
     }
        return "No reviews for this user yet";
  
   }



   private double getRating(RatingScale scale)
		{
			double rating=0;
			switch(scale)
				{
					case ONE: rating= 1.00; break;
					case TWO: rating= 2.00; break;
					case THREE:rating= 3.00; break;
					case FOUR:rating= 4.00; break;
					case FIVE:rating= 5.00; break;
					default: break;
				}
				return rating;
		}
 
    
}