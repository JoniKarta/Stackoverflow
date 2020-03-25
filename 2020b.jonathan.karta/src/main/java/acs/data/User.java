package acs.data;

public class User {

	private UserRole userRole;
	private String userEmail;
	
	
	public User() {
		super();
	}
	
	public User(UserRole userRole, String userEmail) {
		super();
		this.userRole = userRole;
		this.userEmail = userEmail;
	}
	
	public UserRole getUserRole() {
		return userRole;
	}
	
	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "User [userRole=" + userRole + ", userEmail=" + userEmail + "]";
	}
	
	
	
	
}
