public class Staff extends User {
	private String password;


	public Staff(String userID, String userName, String password) {
		super(userID,UserType.STAFF, userName);
		this.password = password;
	}
	public Staff(String userName, String password) {
		super(UserType.STAFF, userName);
		this.password = password;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




}