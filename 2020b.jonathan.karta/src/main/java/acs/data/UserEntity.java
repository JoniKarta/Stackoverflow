package acs.data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="USERS")
public class UserEntity {

	private String email;
	private UserRoleEntity role;
	private String userName;
	private String avatar;
	private Date creation;

	public UserEntity() {
	}

	public UserEntity(String email, UserRoleEntity role, String userName, String avatar) {
		super();
		this.email = email;
		this.role = role;
		this.userName = userName;
		this.avatar = avatar;
	}
	
	@Id
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Enumerated(EnumType.STRING)
	public UserRoleEntity getRole() {
		return role;
	}

	public void setRole(UserRoleEntity role) {
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
	@Temporal(TemporalType.TIMESTAMP)
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
