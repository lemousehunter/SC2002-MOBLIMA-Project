public class MovieBoundary {
    private MovieManager movieMgr;
    private reviewManager reviewMgr;

    public MovieBoundary(MovieManager movieMgr, reviewManager reviewMgr) {
        this.movieMgr = movieMgr;
        this.reviewMgr = reviewMgr;
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

        for(ReviewE vr: this.reviewMgr.getMasterRatings())
        {
            if(vr.getMovieId().equals(movieID))
            {
                System.out.println("Review: " + vr.getReview());
                System.out.println("Rating: " + vr.getRating());
            }
        }

        double avgRating = this.reviewMgr.getAvgRating(movieID);

        if(avgRating != -1.0)
        {
            System.out.printf("Overall Ratings: %.1f / 5\n", avgRating);
        }
        else
        {
            System.out.println("Overall Ratings: NA");
        }
        System.out.println("---------------------------X---------------------------");
    }

    public void PrintMovieList(boolean currentlyShowing) {
        int count = 1;
        System.out.println("List of Movies:");
        if (currentlyShowing) {
            for(MovieEY m: this.movieMgr.getCurrentMovies()) {
                System.out.println(count + ": " + m.getName() + " - " + m.getShowStatus());
            }
        }
        else {
            for(MovieEY m: this.movieMgr.getMasterList()) {
                System.out.println(count + ": " + m.getName() + " - " + m.getShowStatus());
            }
        }
    }

}
