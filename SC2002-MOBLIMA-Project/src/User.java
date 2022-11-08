import java.util.UUID;

public class User {

	private UserType userType;
	private String userName;
	private String userID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public User(UserType userType, String userName) {
		this.userID = UUID.randomUUID().toString();
		this.userType = userType;
		this.userName = userName;
	}

}