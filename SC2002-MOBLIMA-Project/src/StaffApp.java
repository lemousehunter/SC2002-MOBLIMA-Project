import java.util.Scanner;
/**
 * A Staff App Class
 * 
 * <p>
 * A <code>StaffApp</code> class used 
 * perform operations related to Staff application
 * </p>
 * 
 */
public class StaffApp {
    private static StaffApp currentInstance;
    static Scanner sc=new Scanner(System.in);
    private int choice;
    private StaffApp(){}

    /**
     * Method to get the instance of staff App object
     * @return The staff app object
     */
    public static synchronized StaffApp  getInstance() {
        if (currentInstance == null) currentInstance = new StaffApp();
        return currentInstance;
    }
    /**
     * Helper method to request for user choice 
     */
    public void ProcessStaff() {
        System.out.println("Enter choice: ");
        do{
            DisplayUserMenu();
        }while (choice<=10);
    }

    /**
     * Method to print User menu
     */
    private void DisplayUserMenu() {
    }

    /**
     * Method to display staff menu and get user choice
     */
    public void DisplayStaffMenu(){
        System.out.println("==================== Welcome to Staff  APP ====================\n" +
                "1. Create Movie Listing                                              \n" +
                "2. Update Movie listing                                            \n" +
                "3. Remove Movie Listing                                 \n" +
                "4. Check Seat Availability                                \n" +
                "5. Make a Booking                                           \n"+
                "6. View Booking History                              \n" +
                "7. List Top 5 Movies by Sales                             \n" +
                "8. List Top 5 Movies by Ratings                               \n" +
                "9. Review and Rate a Movie                              \n" +
                "===========================================================");
        System.out.println("Enter choice: ");
        while (!sc.hasNextInt()) {
            System.out.println("Please enter an integer value.");
            sc.next();
        }
        choice = sc.nextInt();
    }


        
        /**
         * Method to set ticket prices
         * @param ticket The TicketEY object 
         */
        public void setTicketPrices(TicketEY ticket) {
        // TODO - implement Staff.setTicketPrices
        throw new UnsupportedOperationException();
    }

       
        /**
         * Method to add movies
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

        /**
         * 
         */
        public void getTop5RankingMoviesByTicketSales() {
        // TODO - implement Staff.getTop5RankingMoviesByTicketSales
        throw new UnsupportedOperationException();
    }

        /**
         * 
         */
        public void getTop5RakingMoviesByOverallReviews() {
        // TODO - implement Staff.getTop5RakingMoviesByOverallReviews
        throw new UnsupportedOperationException();
    }

        /**
         *
         * @param movie
         */
        public void updateMovie(MovieEY movie) {
        // TODO - implement Staff.updateMovie
        throw new UnsupportedOperationException();
    }

        /**
         *
         * @param cineplex
         */
        public void addCinePlex(CineplexEY cineplex) {
        // TODO - implement Staff.addCinePlex
        throw new UnsupportedOperationException();
    }

    

}
