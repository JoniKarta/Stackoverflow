package acs.boundaries;

import acs.data.UserRole;

public class UserBoundary {
	private String email;
	private UserRole role;
	private String avatar;

	public UserBoundary() {
		// TODO Auto-generated constructor stub
	}

	public UserBoundary(String email, UserRole role, String avatar) {
		super();
		this.email = email;
		this.role = role;
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
