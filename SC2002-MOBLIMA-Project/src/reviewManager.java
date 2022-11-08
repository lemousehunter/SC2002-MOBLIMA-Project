
import java.util.*;


public class reviewManager {
      
      private ReviewBoundary reviewIO;     
      private HashMap<String,ArrayList< ViewerRatings>>reviewMap;
      private ArrayList<ViewerRatings> masterReviews;
      private ArrayList<Movie> masterMovies;
    

      public reviewManager()
      {
       
        reviewMap=new HashMap<String,ArrayList< ViewerRatings>>();// to build map to get top5movies        
        reviewIO=new ReviewBoundary();
      }
      
      public void setmasterReviews(ArrayList<ViewerRatings> masterReviews) 
      {
        this.masterReviews = masterReviews;
      }
      
      public void setmasterMovies(ArrayList<Movie> masterMovies) 
      {
        this.masterMovies = masterMovies; //(it is passing by value))
      }

      public void addReview(String userID, String movieID)
      {
        RatingScale rating=reviewIO.setScale();
        String reviewString=reviewIO.addReview();
        
      
        ViewerRatings review=new ViewerRatings(userID, movieID,rating,reviewString);       
        this.masterReviews.add(review);
       
        for(Movie m: masterMovies) 
        {
          if(m.getMovieID().equals(movieID))
          {
             m.addViewerRatingsID(review.getViewerRatingId());
          }
           
        }
      }   
      
      public ArrayList<ViewerRatings> getListByMovieID(String movieId) // get list of reviews for specific movieID
      {
        ArrayList<ViewerRatings> reviewList=new ArrayList<ViewerRatings>();
          
        for (ViewerRatings temp :masterReviews) 
        {
          if(temp.getMovieId().equals(movieId))
          {
            reviewList.add(temp);
  
          }        
        }
  
        return reviewList;
      }
        
  
      public ArrayList<ViewerRatings> getListByUserID(String userId)
      {                                                                      
        ArrayList<ViewerRatings> reviewByUser=new ArrayList<ViewerRatings>();
  
          for (ViewerRatings temp :masterReviews) 
          {
            if(temp.getUserId().equals(userId))
            {
                reviewByUser.add(temp);
  
            }
            
          }
  
          return reviewByUser;
  
  
      } 

      public void getAvgRating(String movieId)// get avg rating based on movieID
      {
        double sum=0;
        int i=0;
        ArrayList<ViewerRatings>temp=getListByMovieID(movieId);
        
        if(temp.size()>1)
        {
          for (i=0; i<temp.size();i++) 
          {
            sum+=getRating(temp.get(i).getRating()) ;
          }
          /*below is to calculate to 1 significant figure */
          sum=sum/i;
          sum = sum*10;
          sum = Math.round(sum);
          sum = sum /10; 

          
        }else
        {
          sum=-1.0;
        }
       
        reviewIO.AvgRatingPrint(sum);
        
      }
     
      public void top5masterMovies()
      { 
        
        HashMap <String,Double>avgRatingOfmasterMovies=new HashMap<String,Double>();
        ArrayList<String> top5mv=new ArrayList<String>();
        
        double max=0.00;    
        double avgRating=0;

        buildHashMap(masterReviews);// build hashmap of master reviews based on movieID as key
        for (String key: reviewMap.keySet()) 
        {               
          if(reviewMap.get(key).size()>1)
          {
            for (int i=0; i<reviewMap.get(key).size();i++) 
            {
               avgRating+=getRating(reviewMap.get(key).get(i).getRating()) ;
            }  

          }else
          {
            avgRating=-1.0;
          }
            
            avgRatingOfmasterMovies.put(key,avgRating); 
        }

              
                

        for(int i=0;i<5;i++)
        { 
          try { max=(Collections.max(avgRatingOfmasterMovies.values()));} 
          catch (Exception e) {  reviewIO.Top5MovPrint(top5mv); }
                  
                      
          for (String key :avgRatingOfmasterMovies.keySet()) 
          { 
            if (avgRatingOfmasterMovies.get(key) == max) 
            { 
              avgRatingOfmasterMovies.put(key,-1.0);
              top5mv.add(key);
            }
          }
        }        


        reviewIO.Top5MovPrint(top5mv);
        
       
              
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
 
      private void buildHashMap(ArrayList<ViewerRatings> pass)
      {
        
        for(int i=0;i<pass.size();i++)
        {
            if(reviewMap.containsKey(pass.get(i).getMovieId()))
            {
              reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));
            }else
            {
              reviewMap.put(pass.get(i).getMovieId(), new ArrayList<ViewerRatings>());
              reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));
            }

        }
      
      }  
    
      
}