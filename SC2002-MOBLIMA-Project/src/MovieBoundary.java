import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MovieBoundary {

  private Scanner s;

  private MovieManager movieMgr;
  private ReviewManager reviewMgr;

  private String movieName;
  private String language;
  private String movieType;
  private String movieRating;
  private String showStatus;
  private String synopsis;
  private String director;
  private ArrayList<String> cast;

  public MovieBoundary(MovieManager movieMgr, ReviewManager reviewMgr) {
    this.movieMgr = movieMgr;
    this.reviewMgr = reviewMgr;
    s = new Scanner(System.in);

  }

  public void viewMovieDetails(String movieID) {
    MovieEY movie = this.movieMgr.getMovieByID(movieID);
    System.out.println("Movie Name: " + movie.getName());
    System.out.println("Movie Status: " + movie.getShowStatus());
    System.out.println("Movie Type: " + movie.getMovieType().toString());
    System.out.println("Movie Rating: " + movie.getMovieRating());
    System.out.println("Movie Language: " + movie.getLanguage());
    System.out.println("Synopsis: " + movie.getSynopsis());
    System.out.println("Director: " + movie.getDirector());
    System.out.println("Cast: " + movie.getCast());
    System.out.println("Viewer Review and Ratings:");

    for (ReviewEY vr : this.reviewMgr.getMovieRatings(movie)) {
      if (vr.getMovieId().equals(movieID)) {
        System.out.println("\nReview: " + vr.getReview());
        System.out.println("Rating: " + vr.getRating());
      }
    }

    this.reviewMgr.getAvgRating(movieID);
  }

  public void printMovieList(boolean currentlyShowing) {
    int count = 1;
    System.out.println("\nList of Movies:\n");
    if (currentlyShowing) {
      for (MovieEY m : this.movieMgr.getCurrentMovies()) {
        System.out.println(
          count + ": " + m.getName() + " - " + m.getShowStatus()
        );
        count++;
      }
    } else {
      for (MovieEY m : this.movieMgr.getMasterList()) {
        System.out.println(
          count + ": " + m.getName() + " - " + m.getShowStatus()
        );
        count++;
      }
    }
  }

  public String setMovieName() {
    System.out.print("Enter Movie Name: ");
    movieName = s.nextLine();
    return movieName;
  }

  public String setLanguage(char insertUpdateFlag) {
    if (insertUpdateFlag == 'U'){
        System.out.print("Do you want to update Movie Language ?  (Y | N) ");
        String choice = s.nextLine().toUpperCase();
        if (!choice.equals("Y")){
            return null;
        }

    }

    System.out.print("Enter Movie Language: ");
    language = s.nextLine();
    return language;
    }

  public String setMovieType(char insertUpdateFlag) {
    
    if (insertUpdateFlag == 'U'){
        System.out.print("Do you want to update Movie Type Classification ?  (Y | N) : ");
        String choice = s.nextLine().toUpperCase();
        if (!choice.equals("Y")){
            return null;
        }

    }

    while (true){
        System.out.print("Enter Movie Type Classification (B -> BLOCKBUSTER, 3D -> THREEDIMENSION, D -> DOCUMENTARY) : ");
        movieType = s.nextLine().toUpperCase();
        if (movieType.equals("B")) {
          movieType = "BLOCKBUSTER";
          break;
        }
        if (movieType.equals("3D")) {
          movieType = "THREEDIMENSION";
          break;
        }
        if (movieType.equals("D")) {
          movieType = "DOCUMENTARY";
          break;
        }
        System.out.println("You have entered Invalid Movie Type Classification !!");
    }
    return movieType;  
}

  public String setMovieRating(char insertUpdateFlag) {

    if (insertUpdateFlag == 'U'){
        System.out.print("Do you want to update Movie Rating  ?  (Y | N) :  ");
        String choice = s.nextLine().toUpperCase();
        if (!choice.equals("Y")){
            return null;
        }

    }

    while (true){
        System.out.print("Enter Movie Rating (G | PG | PG13 | R | X) : ");
        movieRating = s.nextLine().toUpperCase();
        if (movieRating.equals("G") | 
            movieRating.equals("PG") |
            movieRating.equals("PG13") |
            movieRating.equals("R") |
            movieRating.equals("X") ) {
          break;
        }
        System.out.println("You have entered Invalid Movie Rating Classification !!");
    }
    return movieRating; 
  }

  public String setShowStatus(char insertUpdateFlag) {

    if (insertUpdateFlag == 'U'){
        System.out.print("Do you want to update Show Status  ?  (Y | N) : ");
        String choice = s.nextLine().toUpperCase();
        if (!choice.equals("Y")){
            return null;
        }

    }

    while (true){
        System.out.print("Enter Show Status (C -> COMINGSOON, P -> PREVIEW, S -> SHOWING, E -> ENDOFSHOWING) : ");
        showStatus = s.nextLine().toUpperCase();
        if (showStatus.equals("C")) {
          showStatus = "COMINGSOON";
          break;
        }
        if (showStatus.equals("P")) {
          showStatus = "PREVIEW";
          break;
        }
        if (showStatus.equals("S")) {
          showStatus = "SHOWING";
          break;
        }
        if (showStatus.equals("E")) {
          showStatus = "ENDOFSHOWING";
          break;
        }		
        System.out.println("You have entered Invalid Show Status Classification !!");
    }
    return showStatus; 
  }

  public String setSynopsis(char insertUpdateFlag) {
    
    if (insertUpdateFlag == 'U'){
        System.out.print("Do you want to update Movie Synopsis  ?  (Y | N) : ");
        String choice = s.nextLine().toUpperCase();
        if (!choice.equals("Y")){
            return null;
        }

    }

    System.out.print("Enter Movie Synopsis: ");
    synopsis = s.nextLine();
    return synopsis;  
}

  public String setDirector(char insertUpdateFlag) {

    if (insertUpdateFlag == 'U'){
        System.out.print("Do you want to update Director Name  ?  (Y | N) : ");
        String choice = s.nextLine().toUpperCase();
        if (!choice.equals("Y")){
            return null;
        }

    }

    System.out.print("Enter Movie Director Name: ");
    synopsis = s.nextLine();
    return synopsis;  
  }

  public ArrayList<String> setCast(char insertUpdateFlag) {

    if (insertUpdateFlag == 'U'){
        System.out.print("Do you want to update Movie cast  ?  (Y | N) :  ");
        String choice = s.nextLine().toUpperCase();
        if (!choice.equals("Y")){
            return null;
        }

    }
    System.out.print("Enter Movie cast (names separated by comma): ");
    String castString = s.nextLine();
    cast = new ArrayList(Arrays.asList(castString.split(",")));
    return cast;    }

public void printDuplicateMovie() {
    System.out.println("Error  : " +movieName + " already exists !!");

}

public void printMissingMovie() {
    System.out.println("Error  : " +movieName + " doesnot  exist !!");

}

public Boolean setCurrentlyShowing() {
    System.out.print("Do you want to see Current Showing Movies ONLY : (Y | N)  : ");
    String currentlyshowing = s.nextLine();
    if (currentlyshowing.equalsIgnoreCase("Y")){
        return true;
    }

    return false;  }

public void printUpdateMovieSuccessMsg() {
  System.out.println(movieName + " Updated !!");

}

public void printAddMovieSuccessMsg() {
  System.out.println(movieName + " Added !!");

}

}
