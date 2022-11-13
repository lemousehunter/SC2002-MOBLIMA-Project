public class Staff extends User {
	
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
	
	private String password;


	/**
	 * Constructor for Staff object that requires 3 parameters
	 * @param userID The userID
	 * @param userName The user name
	 * @param password The password
	 */
	public Staff(String userID, String userName, String password) {
		super(userID,UserType.STAFF, userName);
		this.password = password;
	}
	/**
	 * Constructor for staff object that require 2 parameters
	 * @param userName The user name
	 * @param password The password
	 */
	public Staff(String userName, String password) {
		super(UserType.STAFF, userName);
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