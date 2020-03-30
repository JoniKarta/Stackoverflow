package acs.boundaries;

import acs.data.UserRole;

public class UserBoundary {
	private String email;
	private UserRole role;
	private String userName;
	private String avatar;

	public UserBoundary() {

	}

	public UserBoundary(String email, UserRole role, String userName, String avatar) {
		super();
		this.email = email;
		this.role = role;
		this.userName = userName;
		this.avatar = avatar;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public String toString() {
		return "UserBoundary [email=" + email + ", role=" + role + ", avatar=" + avatar + "]";
	}

}
