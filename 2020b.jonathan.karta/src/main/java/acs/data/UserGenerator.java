package acs.data;


public class UserGenerator {

	private String userEmail;
	
	
	public UserGenerator() {
		super();
	}
	
	public UserGenerator(String userEmail) {
		super();
		this.userEmail = userEmail;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + "]";
	}
	
}
