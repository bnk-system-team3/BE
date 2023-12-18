package ssg.com.houssg.dto;

import java.util.Date;

public class UserDto {

	private String userId;
	private String password;
	private String nickname;
	private String email;
	private String department;
	private Date lastLoginDate;
	private int point;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getLastLoginDate() {
		return lastLoginDate;
	}
	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
	@Override
	public String toString() {
		return "UserDto [userId=" + userId + ", password=" + password + ", nickname=" + nickname + ", email=" + email
				+ ", department=" + department + ", lastLoginDate=" + lastLoginDate + ", point=" + point + "]";
	}

	

	

}
