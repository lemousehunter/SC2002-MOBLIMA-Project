public class Staff extends User {
	private String password;


	public Staff(UserType userType, String userName, String password) {
		super(userType, userName);
		this.password = password;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}




}