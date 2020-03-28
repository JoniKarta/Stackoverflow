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
	
	
	public String getCreatorEmail() {
		return userEmail;
	}
	
	public void setCreatorEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "User [userEmail=" + userEmail + "]";
	}
	
	
	
	
}
