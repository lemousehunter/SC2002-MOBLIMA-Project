import java.util.Scanner;

public class BGetMovieInputB extends BookingBoundary implements GetStringInputB {
    Scanner sc;

    public BGetMovieInputB(MovieManager movieMgr, MovieBoundary movieB) {
        super(movieMgr, movieB);
    }

    @Override
    public String get() { // converts idx from user input to movieID
        this.print("Which movie would you like to book for? Please enter the corresponding integer.");
        this.movieB.printMovieList(true);
        int idx = sc.nextInt();
        return this.movieMgr.getMovieIDFromCurrentShowingIDX(idx);
    }
}
