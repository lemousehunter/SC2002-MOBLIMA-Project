import java.util.Scanner;

public class BookingBoundary extends Boundary{
    MovieManager movieMgr;
    MovieBoundary movieB;

    public BookingBoundary(MovieManager movieMgr, MovieBoundary movieB) {
        this.movieMgr = movieMgr;
        this.movieB = movieB;
    }
}

