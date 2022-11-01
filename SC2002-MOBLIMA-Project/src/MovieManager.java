import java.util.ArrayList;
import java.util.Scanner;

public class MovieManager {

    static Scanner sc = new Scanner(System.in);
    private MovieType movieType;
    private MovieRating movieRating;
    private ShowStatus showStatus;

    
    public Movie getMovieByID(String movieID, ArrayList<Movie> movie) {
        for(Movie m: movie) {
            if(movieID.equals(m.getMovieID()))
                return m;
            else
                System.out.println("No movie exists with this ID!");
        }
        return null;
    }
    
    public void addMovie(ArrayList<Movie> masterMovies) {
        System.out.println("Enter Movie Name: ");
        String name = sc.nextLine();
        System.out.println("Enter Movie Language: ");
        String movieLanguage = sc.nextLine();
        System.out.println("Enter Movie Type: 1 - Blockbuster, 2 - 3D, 3 - Documentary");
        int ch1 = sc.nextInt();
        switch(ch1)
        {
            case 1: movieType = MovieType.BLOCKBUSTER;
            break;
            case 2: movieType = MovieType.THREEDIMENSION;
            break;
            case 3: movieType = MovieType.DOCUMENTARY;
        }
        System.out.println("Enter Movie Rating: 1 - G, 2 - PG, 3 - PG-13, 4 - R, 5 - X");
        int ch2 = sc.nextInt();
        switch(ch2)
        {
            case 1: movieRating = MovieRating.G;
            break;
            case 2: movieRating = MovieRating.PG;
            break;
            case 3: movieRating = MovieRating.PG13;
            break;
            case 4: movieRating = MovieRating.R;
            break;
            case 5: movieRating = MovieRating.X;
        }
        System.out.println("Enter Show Status: 1 - Coming Soon, 2 - Preview, 3 - Showing, 4 - End of Showing");
        int ch3 = sc.nextInt();
        switch(ch3)
        {
            case 1: showStatus = ShowStatus.COMINGSOON;
            break;
            case 2: showStatus = ShowStatus.PREVIEW;
            break;
            case 3: showStatus = ShowStatus.SHOWING;
            break;
            case 4: showStatus = ShowStatus.ENDOFSHOWING;
        }
        System.out.println("Enter Synopsis: ");
        String synopsis = sc.nextLine();
        System.out.println("Enter Director: ");
        String director = sc.nextLine();
        ArrayList<String> cast = new ArrayList<String>();
        String temp_cast;
        System.out.println("Enter the Cast of the Movie (Enter NA when done");
        while(true){
            temp_cast = sc.nextLine();
            if(temp_cast.equalsIgnoreCase("NA"))
                break;            
            else
                cast.add(temp_cast);
        }
        /*ViewerRatings temp = new ViewerRatings();
        System.out.println("Enter Viewer Ratings: (Enter NA when done)");
        ArrayList<ViewerRatings> vr = new ArrayList<ViewerRatings>();
        String review = "";
        while(true) { //NEEED TO COMPLETE THIS
            System.out.println("Enter Review");
            review = sc.nextLine();
            if(review.equalsIgnoreCase("NA"))
                break;
            else
                temp.setReview(review);
            System.out.println("Enter Ratings from 1 to 5");
            int ch4 = sc.nextInt();
            switch(ch4)
            {
                case 1: temp.setRating(RatingScale.ONE);
                break;
                case 2: temp.setRating(RatingScale.TWO);
                break;
                case 3: temp.setRating(RatingScale.THREE);
                break;
                case 4: temp.setRating(RatingScale.FOUR);
                break;
                case 5: temp.setRating(RatingScale.FIVE);
                break;
            }
            vr.add(temp);*/
        
        
        Movie movie = new Movie(name, movieLanguage, movieType, movieRating, showStatus, synopsis, director, cast);
        masterMovies.add(movie);
    }

    public void updateMovie(Movie movie) {
        System.out.println("The current status of the Movie is " + movie.getShowStatus());
        System.out.println("Enter 1 for Coming Soon, 2 for Preview, 3 for Showing, 4 for End of Showing");
        int ch = sc.nextInt();
        switch(ch)
        {
            case 1: movie.setShowStatus(ShowStatus.COMINGSOON);
            break;
            case 2: movie.setShowStatus(ShowStatus.PREVIEW);
            break;
            case 3: movie.setShowStatus(ShowStatus.SHOWING);
            break;
            case 4: movie.setShowStatus(ShowStatus.ENDOFSHOWING);
            break;
            default: System.out.println("Invalid input");
        }
    }

    public void removeMovie(Movie movie)
    {
        movie.setShowStatus(ShowStatus.ENDOFSHOWING);
    }
    
    public void viewMovies(ArrayList<Movie> movie, ArrayList<ViewerRatings> ratings) {
        for(Movie m: movie) {
            m.viewMovieDetails(ratings);
            System.out.println("---------------------------X---------------------------");
        }
    }

    public void movieListing(ArrayList<Movie> movie) {
        System.out.println("List of Movies:");
        for(Movie m: movie) {
            System.out.println(m.getName() + " - " + m.getShowStatus());
        }
    }

    public void searchMovie(ArrayList<Movie> movie) {
        System.out.println("Enter the movie name you want to search for: ");
        String user_input = sc.nextLine();
        for(Movie m: movie) {
            if(m.getName().equalsIgnoreCase(user_input)) {
                System.out.println("Movie Found!");
                System.out.println(m.getName() + " - " + m.getShowStatus());
            }
            else
                System.out.println("Movie not Found!");
                
        }
    }
}

