import java.util.*;

public class ReviewManager implements Manager{

  private ReviewBoundary reviewIO;    
  private ArrayList<User> masterUserList;
  private ArrayList<CineplexEY> masterCineplexes;
  private ArrayList<ScreenEY> masterScreens;
  private ArrayList<BookingEY> masterBookings;
  private ArrayList<ShowEY> masterShows;
  private ArrayList<MovieEY> masterMovies;
  private ArrayList<String> masterHolidaysList;
  private ArrayList<ReviewEY> masterRatings;
  
  public ReviewManager()
      {          
        reviewIO=new ReviewBoundary();
      }

  @Override
    public void setMasterLists(
    ArrayList<User> masterUserList,
    ArrayList<CineplexEY> masterCineplexes,
    ArrayList<ScreenEY> masterScreens,
    ArrayList<BookingEY> masterBookings,
    ArrayList<ShowEY> masterShows,
    ArrayList<MovieEY> masterMovies,
    ArrayList<String> masterHolidaysList,
    ArrayList<ReviewEY> masterRatings
    ) {
    this.masterUserList = masterUserList;
    this.masterCineplexes = masterCineplexes;
    this.masterScreens = masterScreens;
    this.masterBookings = masterBookings;
    this.masterShows = masterShows;
    this.masterMovies = masterMovies;
    this.masterHolidaysList = masterHolidaysList;
    this.masterRatings = masterRatings;
    
  }

  public void addReview(String userID, String movieID) {
    double scale=reviewIO.setScale();
    String reviewString=reviewIO.addReview();
    
    ReviewEY review = new ReviewEY(userID, movieID, scale, reviewString);
    masterRatings.add(review);
    for (MovieEY m : masterMovies)
    {
      if (m.getMovieID().equals(movieID)) 
      {
         m.addViewerRatingsID(review.getViewerRatingId());
      }
      
    }
  }

  public ArrayList<ReviewEY> getListByMovieID(String movieId) {
    ArrayList<ReviewEY> reviewList = new ArrayList<ReviewEY>();

    for (ReviewEY temp : masterRatings)
    {
      if (temp.getMovieId().equals(movieId)) 
      {
        reviewList.add(temp);
      }
    }

    return reviewList;
  }
 
  public ArrayList<ReviewEY> getListByUserID(String userId) {
    ArrayList<ReviewEY> reviewByUser = new ArrayList<ReviewEY>();

    for (ReviewEY temp : masterRatings)
    {
      if (temp.getUserId().equals(userId)) 
      {
        reviewByUser.add(temp);
      }
    }

    return reviewByUser;
  }

  
  public void getAvgRating(String movieId) {
    double sum = 0;
    int i = 0;
    ArrayList<ReviewEY> temp = getListByMovieID(movieId);
    if(temp.size()>1)
    {
      for (i = 0; i < temp.size(); i++) {
        sum += temp.get(i).getRating();
      }

      /*below is to calculate to 1 significant figure */
      sum = sum / i;
      sum = sum * 10;
      sum = Math.round(sum);
      sum = sum / 10;
      
    }else
    {
      sum=-1.0;
    }
    reviewIO.AvgRatingPrint(sum);
  }

  public void top5MoviesByViewerRatings() // prints the movie name
  { 
    HashMap<String,ArrayList<ReviewEY>>reviewMap=buildHashMap(masterRatings);// build hashmap of master reviews based on movieID as key
    HashMap <String,Double>avgRatingOfmasterMovies=new HashMap<String,Double>();        
    ArrayList<String> top5mv=new ArrayList<String>();        
    double max=0.00;    
    double avgRating=0;

    
    for (String key: reviewMap.keySet()) 
    {             
      avgRating=0;  
      if(reviewMap.get(key).size()>1)
      {
        for (int i=0; i<reviewMap.get(key).size();i++) 
        {
           avgRating+=reviewMap.get(key).get(i).getRating() ;
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
          
          for (MovieEY m : masterMovies)
          {
            if(m.getMovieID().equals(key))
            {
                top5mv.add(m.getName());// adds the movie name 
                break;
            }
          }
          break;
          
        }
      }
    }        
    reviewIO.Top5MovPrint(top5mv);
  }      

  private  HashMap<String,ArrayList<ReviewEY>>  buildHashMap(ArrayList<ReviewEY> pass)
  {
    HashMap<String,ArrayList<ReviewEY>>reviewMap=new HashMap<String,ArrayList<ReviewEY>>();
    for(int i=0;i<pass.size();i++)
    {
      if (reviewMap.containsKey(pass.get(i).getMovieId())) 
      {
        reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));
      } else 
      {
        reviewMap.put(pass.get(i).getMovieId(), new ArrayList<ReviewEY>());
        reviewMap.get(pass.get(i).getMovieId()).add(pass.get(i));
      }
    }
    return reviewMap;
  }

  public ArrayList<ReviewEY> getMovieRatings(MovieEY movie)
  {
    ArrayList<ReviewEY> movieViewerRatings = new  ArrayList<ReviewEY>();
    for (String ratingID : movie.getViewerRatingsID()){
      for (ReviewEY rating : masterRatings) {
          if (rating.getViewerRatingId().equals(ratingID)){
            movieViewerRatings.add(rating);
            break;
          }
      }
    }

    return movieViewerRatings;
  }

}
