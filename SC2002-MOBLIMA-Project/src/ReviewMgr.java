

import java.util.*;

public class ReviewMgr {

    private String review;
    private RatingScale rating;
    private ViewerRatings reviewer;
    public static ArrayList<ViewerRatings> ListOfViewerRatingObj=new ArrayList<ViewerRatings>();
     private Scanner s=new Scanner(System.in);
     private Scanner q=new Scanner(System.in);
  
   
    

   public void addReview(String userID, String movieID)
    {
      review=setReview();
      rating=setScale();
      reviewer=new ViewerRatings(userID, movieID, rating,review);
      addToList(reviewer); //same userID cannot review twice
      ListOfViewerRatingObj.add(reviewer);//same userID person can review twice
      
        
    }   

    private String setReview() //set the review
  {
    String review;
    System.out.println("Please enter review");
    review=s.nextLine();
    s.close();
    return review;
    
  }
   
    private RatingScale setScale() //convert int rating to enum to initialize obj 
    {
      int scale=0;
      
       RatingScale rate=RatingScale.ONE;
			while(scale>5||scale<1)
			{

				System.out.println("Set rating scale (1-5)");
        scale=q.nextInt();
        q.close();
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
  
  private double getRating(RatingScale scale)// method to convert rating class enum for  avg calculation
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

  private void addToList(ViewerRatings pass)// add to list if its not the same person reviewing the same movie
  {
   boolean presentFlag=false;
    
    for(ViewerRatings temp:ListOfViewerRatingObj)
    {
        if(temp.getMovieId().equals(reviewer.getMovieId())&&temp.getUserId().equals(reviewer.getUserId()))
        {

          presentFlag=true;
        }

    }

    if(presentFlag==false)
    {

        ListOfViewerRatingObj.add(pass);
    }else{

      System.out.println("User already gave a review for this movie");
    }
   
  }
 
    
   public ArrayList<ViewerRatings> getListByMovieID(String movieId)//pass a list with same movieid
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
    

   public ArrayList<ViewerRatings> getListByUserID(String userId)//pass a list with same userId
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

   public double getAvgRating(String movieId)// get the average rating
   {
     double sum=0;
     int i=0;
     ArrayList<ViewerRatings>temp=getListByMovieID(movieId);
     for (i=0; i<temp.size();i++) {
       sum+=getRating(temp.get(i).getRating()) ;
    }

      sum=sum/i;// minor computation for rounding
      sum = sum*10;
      sum = Math.round(sum);
     sum = sum /10;
     return sum;

   }
   public String getViewerRatingID(String userId)//get entity id of existing reviews(not necessary)
   {
    
     for (ViewerRatings temp:ListOfViewerRatingObj) {
       if(temp.getUserId().equals(userId))
       {
          return temp.getUserId();

       }
     }
        return "No reviews for this user yet";
  
   }



   
 
    
}
