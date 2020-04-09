package acs.data;

import java.util.Date;

import acs.boundaries.UserRole;

public class UserEntity {

	private String email;
	private UserRole role;
	private String userName;
	private String avatar;
	private Date creation;

	public UserEntity() {
	}

	public UserEntity(String email, UserRole role, String userName, String avatar) {
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

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	@Override
	public String toString() {
		return "UserEntity [email=" + email + ", role=" + role + ", userName=" + userName + ", avatar=" + avatar + "]";
	}

}
