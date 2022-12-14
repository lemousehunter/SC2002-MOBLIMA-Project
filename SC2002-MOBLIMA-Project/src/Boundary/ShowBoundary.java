package Boundary;

import Controller.ShowManager;

import java.util.ArrayList;
	/**
	 * A Show boundary object that extends Boundary.Boundary class and implements Boundary.BaseBoundary interface
	 * 
	 * <p>
	 * A <code>Boundary.ShowBoundary</code> object used to process all
	 * Show input and output
	 * </p>
	 */
public class ShowBoundary extends Boundary implements BaseBoundary{
    /**
     * to contain show manager object to process all show related objects
     */
    ShowManager showManager;
    /**
     *{@inheritDoc}
     */
    @Override
    public void setManagers() {
        this.showManager = this.getCentralManager().getShowMgr();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBoundaries() {

    }

    /**
     * Method to add a show from user
     */
    public void addShow() {
        boolean filter = false;
        String cineplexName = this.getCineplexName(filter);
        String screenName   = this.getScreenName(filter);
        String movieName    = this.getMovieName(filter);
        String showDate     = this.getShowDate(filter);
        String showTime     = this.getShowTime(filter);
        int retcode         = this.showManager.addShow(cineplexName, screenName, movieName, showDate, showTime);
        switch (retcode) {
            case 0:
                this.println(
                    "\nShow for " +
                    movieName +
                    "at Screen " +
                    screenName +
                    " on " +
                    showDate +
                    " @ " +
                    showTime +
                    " Added "
                );
                break; 
            case 1:
                this.println("\nError : Cineplex " + cineplexName + " not found!");
                break;
            case 2:
                this.println("\nError : Screen  " + screenName + " not found!");
                break;
            case 3:
                this.println("\nError : Move  " + movieName + " not found!");
                break;
            case 4:
                this.println("\nError : Show at Screen " +
                    screenName +
                    " on " +
                    showDate +
                    " @ " +
                    showTime +
                    " already exists !!");
                break;
            }
    }

    /**
     * Method to set new movie from user
     * @return The user input movie choice
     */
    public String setNewMovieName() {
        String choice = this.getInputLine("Do you want to change the Movie  ?  (Y | N) :  ");
        if (!choice.equals("Y")) {
            return this.getInputLine("\nPlease enter Movie Name  : ");
        }
        return "";
      }
      /**
       * Method to set new show time from user
     * @return The user input show time
     */
    public String setNewShowTime() {
        String choice = this.getInputLine("Do you want to change the Show Time  ?  (Y | N) :  ");
        if (choice.equals("Y")) {
            return this.getInputLine("\nPlease enter Show Time  : ");
        }
        return "";  
      }
    
    /**
     * Method to get show time from user
     * @param filter The flag to filter
     * @return The user show time input
     */
    private String getShowTime(Boolean filter) {
        if (filter) {
            String choice = this.getInputLine("Do you want to filter by Show Time  ?  (Y | N)  :  ");
            if (!choice.equals("Y")) {
              return "";
            }
        }
        return this.getInputLine("\nPlease enter Show Time : ");
    }
    /**
     * Method to get show date from user
     * @param filter The flag to filter
     * @return The user show date input
     */
    private String getShowDate(Boolean filter) {
        if (filter) {
            String choice =this.getInputLine("Do you want to filter by Show Date  ?  (Y | N)  :  ");
            if (!choice.toUpperCase().equals("Y")) {
              return "";
            }
        }
        return this.getInputLine("\nPlease enter Show Date : ");

    }
    /**
     * Method to get movie name from user
     * @param filter The flag to filter
     * @return The user movie name input
     */
    private String getMovieName(Boolean filter) {
        if (filter) {
            String choice = this.getInputLine("Do you want to filter by Movie Name  ?  (Y | N) :  ");
            if (!choice.toUpperCase().equals("Y")) {
              return "";
            }
        }
        return this.getInputLine("\nPlease enter Movie Name  : ");

    }

    
    /**
     * Method to get movie name from user
     * @param filter The flag to filter
     * @return The user movie name input
     */
    private String getScreenName(Boolean filter) {
        if (filter) {
            String choice =  this.getInputLine("Do you want to filter by Screen  ?  (Y | N)     :  ");
            if (!choice.toUpperCase().equals("Y")) {
              return "";
            }
       }
       return this.getInputLine("\nPlease enter Screen Name  : ");

    }

    /**
     * Method to get cineplex name
     * @param filter The flag to filter
     * @return The cineplex name
     */
    private String getCineplexName(Boolean filter) {
        if (filter) {
            String choice = this.getInputLine("Do you want to filter by Cineplex  ?  (Y | N) :  ");
            //choice = this.getScanner().nextLine();
            if (!choice.toUpperCase().equals("Y")) {
              return "";
            }
        }
        return this.getInputLine("\nPlease enter Cineplex Name : ");
    }

    /**
     * Method to perform operations based on user menu choice
     * 
     */
    public void showOperations() {
        int showChoice = 0;
        while (showChoice != 5) {
            showChoice = this.getShowMenuChoice();
            switch (showChoice) {
                case 1:
                    this.addShow();
                    break;
                case 2:
                    this.updateShow();
                    break;
                case 3:
                    this.removeShow();
                    break;
                case 4:
                    this.listShows();
                    break;
                case 5:
                    break;
            }
        }

    }

    /**
     * Method to list shows for filtering
     */
    private void listShows() {
        boolean filter = true;
        String cineplexName ="", screenName ="", movieName ="", showDate ="",showTime =""; 
        cineplexName = this.getCineplexName(filter);
        screenName   = this.getScreenName(filter);
        movieName    = this.getMovieName(filter);
        showDate     = this.getShowDate(filter);
        showTime     = this.getShowTime(filter);
        int retCode  = this.showManager.validateList(cineplexName, screenName, movieName, showDate, showTime);
        switch (retCode) {
            case 0:
                break;
            case 1:
                this.println("\nError : Enter atleast Cineplex name | Screen Name | Movie Name | Show Date");
                break;
            case 2:
                this.println("\nError : Cineplex " + cineplexName + " not found!");
                break;
            case 3:
                this.println("\nError : Screen  " + screenName + " not found!");
                break;
            case 4:
                this.println("\nError : Movie  " + movieName + " not found!");
                break;
            }
        if (retCode > 0 ) { return;}
        ArrayList<String> Printlist = this.showManager.listShows(cineplexName, screenName, movieName, showDate, showTime);
        this.println("\n The following are the Shows: \n");
        for( String printLine: Printlist){
            this.println(printLine);
        }
    }

    /**
     * Method for user to remove a show
     */
    private void removeShow() {
        boolean filter = false;
        String cineplexName = this.getCineplexName(filter);
        String screenName   = this.getScreenName(filter);
        String movieName    = this.getMovieName(filter);
        String showDate     = this.getShowDate(filter);
        String showTime     = this.getShowTime(filter);
        int retCode         = this.showManager.removeShow(cineplexName, screenName, movieName, showDate, showTime);
        switch (retCode) {
            case 0:
                this.println(
                    "\nShow for " +
                    movieName +
                    "at Screen " +
                    screenName +
                    " on " +
                    showDate +
                    " @ " +
                    showTime +
                    " Removed "
                );
                break; 
            case 1:
                this.println("\nError : Enter atleast Cineplex name | Screen Name | Movie Name | Show Date");
                break;
            case 2:
                this.println("\nError : Cineplex " + cineplexName + " not found!");
                break;
            case 3:
                this.println("\nError : Screen  " + screenName + " not found!");
                break;
            case 4:
                this.println("\nError : Move  " + movieName + " not found!");
                break;
            case 5:
                this.println("\nError : No Show at Screen ");
                break;
        
            }
        }
            
    

    /**
     * Method for user to update show
     */
    private void updateShow() {
        boolean filter = false;
        String cineplexName = this.getCineplexName(filter);
        String screenName   = this.getScreenName(filter);
        String movieName    = this.getMovieName(filter);
        String showDate     = this.getShowDate(filter);
        String showTime     = this.getShowTime(filter);
        String newMovieName = this.setNewMovieName();
        String newShowTime  = this.setNewShowTime();
        
        int retCode         = this.showManager.updateShow(cineplexName, screenName, movieName, showDate, showTime, newShowTime, newMovieName);
        switch (retCode) {
            case 0:
                this.println(
                    "\nShow for " +
                    movieName +
                    "at Screen " +
                    screenName +
                    " on " +
                    showDate +
                    " @ " +
                    showTime +
                    " Updated "
                );
                break; 
            case 1:
                this.println("\nError : Enter atleast Cineplex name | Screen Name | Movie Name | Show Date");
                break;
            case 2:
                this.println("\nError : Cineplex " + cineplexName + " not found!");
                break;
            case 3:
                this.println("\nError : Screen  " + screenName + " not found!");
                break;
            case 4:
                this.println("\nError : Move  " + movieName + " not found!");
                break;
            case 5:
                this.println("\nError : Show at Screen ");
                break;
            case 6:
                this.println("\nNo updates! ");
                break;
            }
    }

    /**
     * Method to display menu and get user choice
     * @return The user choice
     */
    public int getShowMenuChoice() {

        return this.getInputInt(
            "\n========================= Welcome to Staff App =========================\n" +
            "1.  Add Show                                              \n" +
            "2.  Update Show                                              \n" +
            "3.  Remove Show                                          \n" +
            "4.  List Shows                                               \n" +
            "5.  Return to Staff Menu                                              \n" +
            "========================================================================\n"+
          "Enter choice: "
        );
      }

    /**
     * Method to print show dates
     * @param cineplexID The cineplexID
     * @param movieID The movieID
     */
    public void printShowDates(String cineplexID, String movieID) {
        int count = 1;
        this.println("\nList of Show Dates :\n");
        ArrayList<String> lines;
      
        for (String line: showManager.getShowDateForMovieAtCineplex(cineplexID,movieID)) {
            this.println(line);
        }
    }

    /**
     * Method to print show times
     * @param cineplexID The cineplexID
     * @param movieID The movieID
     * @param showDate The show date
     */
    public void printShowTimes(String cineplexID, String movieID, String showDate) {

        int count = 1;
        this.println("\nList of Show Times and Screen :\n");
        ArrayList<String> lines;
      
        for (String line: showManager.getShowTimeForMovieAtCineplex(cineplexID,movieID,showDate)) {
            this.println(line);
        }
    }

    /**
     * Method to show seat layout
     * @param showID The showID
     */
    public void ShowSeatLayout(String showID) {
        int count = 1;
        this.println("\nList of Show Times and Screen :\n");
        ArrayList<String> lines;
        for (String line: this.showManager.ShowSeatLayout(showID)) {
            this.println(line);
        }
        
    }
}
