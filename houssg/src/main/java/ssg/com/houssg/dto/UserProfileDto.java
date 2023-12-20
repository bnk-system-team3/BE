package ssg.com.houssg.dto;

import java.util.List;

public class UserProfileDto {
	private String userId;
    private String nickname;
    private String department;
    private String email;
    private int point;
    private String position;
    private List<String> techStack;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public List<String> getTechStack() {
		return techStack;
	}
	public void setTechStack(List<String> techStack) {
		this.techStack = techStack;
	}
	@Override
	public String toString() {
		return "UserProfileDto [userId=" + userId + ", nickname=" + nickname + ", department=" + department + ", email="
				+ email + ", point=" + point + ", position=" + position + ", techStack=" + techStack + "]";
	}
    
    
}
