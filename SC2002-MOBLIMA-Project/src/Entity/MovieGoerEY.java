package Entity;

import java.util.ArrayList;
import Entity.UserEY;
import Enum.MovieGoerAgeEN;
import Enum.UserTypeEN;
/**
 * A MovieGoerEY object
 * 
 * <p>
 * A <code>MovieGoerEY</code> object contains all the parameters necessary
 * to store MovieGoer user details
 * </p>
 * 
 */

public class MovieGoerEY extends UserEY {
    /**
     * To contain the email of the MovieGoer
     */
    public final String email;
    /**
     * To contain the mobile number of the MovieGoer
     */
    public final String mobileNumber;
    /**
     * To contain the age of the MovieGoer
     */
    public final Integer age;
    /**
     * To contain the bookings of the MovieGoer
     */
    public final ArrayList<String> bookings;


    private MovieGoerAgeEN ageType;

 
    /**
     * Constructor for MovieGoerEY
     * @param userID The userID
     * @param name The user name
     * @param email The email of the user
     * @param mobileNumber The mobile number of the user 
     * @param age The age of the user
     * @param bookings The bookings by the user
     */
    public MovieGoerEY(String userID, String name, String email, String mobileNumber, Integer age, ArrayList<String> bookings)
    {
        super(userID, UserTypeEN.MOVIEGOER, name);
           
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.age = age;
        this.setUserID(userID);
        if(age<=5){
            this.ageType = MovieGoerAgeEN.CHILD;
        }
        else if(age>5 && age<=21){
            this.ageType = MovieGoerAgeEN.STUDENT;
        }
        else if(age>21 && age<=59)
            this.ageType = MovieGoerAgeEN.ADULT;
        else
            this.ageType = MovieGoerAgeEN.SENIOR;
        this.bookings=bookings;

    }

    /**
     * method to get age type of the movie goer
     * @return The age type (ADULT,SENIOR,CHILD,STUDENT)
     */
    public String getAgeType() {
        return this.ageType.toString();
    }
    /**
     * method to get email ID
     * @return The movie goer email ID
     */
    public String getEmailID() { return this.email; }
    /**
     * method to get the mobile number of the movie goer
     * @return The mobile number 
     */
    public String getMobileNumber() { return this.mobileNumber; }

    /**
     * method to get the age of the movie goer
     * @return The age of the movie goer
     */
    public Integer getAge() { return this.age; }

    /**
     * method to get the bookings of the movie goer
     * @return The bookings
     */
    public ArrayList<String> getBookings() { return this.bookings; }

    /**
     * Method to add bookingID
     * @param bookingID The booking ID
     */
    public void addBookingID (String bookingID) {
        bookings.add(bookingID);
    }

}

