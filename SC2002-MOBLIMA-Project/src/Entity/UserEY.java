package Entity;

import Enum.UserTypeEN;
import java.util.UUID;

/**
 * A User Object
 * 
 * <p>
 * A <code>User</code> object contains all the parameters
 * used to store user information
 * </p>
 * 
 */

public class UserEY {

	/**
	 * A user type enum that has Moviegoer type and Staff type
	 */
	private UserTypeEN userType;
	/**
	 * To store username
	 */
	private String userName;
	/**
	 * to store user ID
	 */
	private String userID;

	
	/**
	 * Constructor for User object(require 2 parameter)
	 * @param userType type of user (Moviegoer or staff)
	 * @param userName user name of the user
	 */
	public UserEY(UserTypeEN userType, String userName) {
		this.userID = UUID.randomUUID().toString();
		this.userType = userType;
		this.userName = userName;
	}
	/**
	 * Constructor for User object(require 3 parameter)
	 * @param userID user ID of the user
	 * @param userType type of user (Moviegoer or staff)
	 * @param userName	user name of the user
	 */
	public UserEY(String userID, UserTypeEN userType, String userName) {
		if (userID.equals("")) {
			this.userID = UUID.randomUUID().toString();
		} else {
			this.userID = userID;
		}
		this.userType = userType;
		this.userName = userName;

	}
	
	/** 
	 * Method to get the user ID
	 * @return The UserID as a String 
	 */
	public String getUserID() {
		return userID;
	}

	
	/** 
	 * Method to set the User ID
	 * @param userID The UserID to be set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	
	/** 
	 * Method to get user type
	 * @return UserType return enum value of Staff or Moviegoer
	 */
	public UserTypeEN getUserType() {
		return userType;
	}

	
	/** 
	 * Method to set user type
	 * @param userType enum value of user type
	 */
	public void setUserType(UserTypeEN userType) {
		this.userType = userType;
	}

	
	/** 
	 * Method to get user name
	 * @return String The user name of the user
	 */
	public String getUserName() {
		return userName;
	}

	
	/** 
	 * Method to set user name	
	 * @param userName the name of the user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

}