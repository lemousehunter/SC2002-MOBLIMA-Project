package Entity;
import Enum.UserTypeEN;
/**
 * A Staff object that is derived from User object  
 * 
 * <p>
 * A <code>Staff</code> object used to store all the parameters
 * about a Staff
 * </p>
 * 
 * 
 */

public class StaffEY extends UserEY {
	
	
	
	private String password;


	/**
	 * Constructor for Staff object that requires 3 parameters
	 * @param userID The userID
	 * @param userName The user name
	 * @param password The password
	 */
	public StaffEY(String userID, String userName, String password) {
		super(userID, UserTypeEN.STAFF, userName);
		this.password = password;
	}
	/**
	 * Constructor for staff object that require 2 parameters
	 * @param userName The user name
	 * @param password The password
	 */
	public StaffEY(String userName, String password) {
		super(UserTypeEN.STAFF, userName);
		this.password = password;
	}


	/**
	 * Method to get Staff password
	 * @return The password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Method to set Password for Staff
	 * @param password The password
	 */
	public void setPassword(String password) {
		this.password = password;
	}




}