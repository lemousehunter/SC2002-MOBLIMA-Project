import java.util.Scanner;

public class StaffApp {
    private static StaffApp currentInstance;
    static Scanner sc=new Scanner(System.in);
    private StaffApp(){}

    public static synchronized StaffApp  getInstance() {
        if (currentInstance == null) currentInstance = new StaffApp();
        return currentInstance;
    }
    public void ProcessStaff() {
        System.out.println("Enter choice: ");
        do{
            DisplayUserMenu();
        }while (choice<=10);
    }

    private void DisplayUserMenu() {
    }

    public void DisplayStaffMenu(){
        System.out.println("==================== Welcome to Staff  APP ====================\n" +
                " 1. View Movie Details                                              \n" +
                " 2. Moving Listing                                            \n" +
                " 3. Search Movie                                \n" +
                " 4. Check Seat Availability                                \n" +
                "5. Make a Booking                                           \n"+
                "6. View Booking History                              \n" +
                "7. List Top 5 Movies by Sales                             \n" +
                " 8. List Top 5 Movies by Ratings                               \n" +
                " 9. Review and Rate a Movie                              \n" +
                "===========================================================");
        System.out.println("Enter choice: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter an integer value.");
            sc.next();
        }
        int subchoice = sc.nextInt();
    }


        /**
         *
         * @param ticket
         */
        public void setTicketPrices(Ticket ticket) {
        // TODO - implement Staff.setTicketPrices
        throw new UnsupportedOperationException();
    }

        /**
         *
         * @param name
         * @param type
         * @param movieRating
         * @param showStatus
         * @param synopsis
         * @param director
         * @param cast
         */
        public void addMovies(int name, int type, int movieRating, int showStatus, int synopsis, int director, int cast) {
        // TODO - implement Staff.addMovies

        throw new UnsupportedOperationException();
    }

        public void getTop5RankingMoviesByTicketSales() {
        // TODO - implement Staff.getTop5RankingMoviesByTicketSales
        throw new UnsupportedOperationException();
    }

        public void getTop5RakingMoviesByOverallReviews() {
        // TODO - implement Staff.getTop5RakingMoviesByOverallReviews
        throw new UnsupportedOperationException();
    }

        /**
         *
         * @param movie
         */
        public void updateMovie(Movie movie) {
        // TODO - implement Staff.updateMovie
        throw new UnsupportedOperationException();
    }

        /**
         *
         * @param cinePlex
         */
        public void addCinePlex(CinePlex cinePlex) {
        // TODO - implement Staff.addCinePlex
        throw new UnsupportedOperationException();
    }

    }

}
