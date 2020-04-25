package acs.data;

public class Creator {

	private String userEmail;

	public Creator() {
		super();
	}

	public Creator(String userEmail) {
		super();
		this.userEmail = userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + "]";
	}

}
