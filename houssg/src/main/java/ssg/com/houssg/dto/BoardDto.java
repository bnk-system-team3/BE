package ssg.com.houssg.dto;

import java.util.Date;

public class BoardDto {

	private int boardId;
	private String title;
	private String description;
	private int participantCnt;
	private int recruitCnt;
	private int onOffStatus;
	private int activeFlag;
	private String chattingUrl;
	private String startDate;
	private String endDate;
	private Date createDate;
	private String userId;
	private String dueDate;
	private String location;
	private int viewCnt;
	private String category;
	private String teamContent;

	
	
	public String getTeamContent() {
		return teamContent;
	}

	public void setTeamContent(String teamContent) {
		this.teamContent = teamContent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getParticipantCnt() {
		return participantCnt;
	}

	public void setParticipantCnt(int participantCnt) {
		this.participantCnt = participantCnt;
	}

	public int getRecruitCnt() {
		return recruitCnt;
	}

	public void setRecruitCnt(int recruitCnt) {
		this.recruitCnt = recruitCnt;
	}

	public int getOnOffStatus() {
		return onOffStatus;
	}

	public void setOnOffStatus(int onOffStatus) {
		this.onOffStatus = onOffStatus;
	}

	public int getActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(int activeFlag) {
		this.activeFlag = activeFlag;
	}

	public String getChattingUrl() {
		return chattingUrl;
	}

	public void setChattingUrl(String chattingUrl) {
		this.chattingUrl = chattingUrl;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	@Override
	public String toString() {
		return "BoardDto [boardId=" + boardId + ", title=" + title + ", description=" + description
				+ ", participantCnt=" + participantCnt + ", recruitCnt=" + recruitCnt + ", onOffStatus=" + onOffStatus
				+ ", activeFlag=" + activeFlag + ", chattingUrl=" + chattingUrl + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", createDate=" + createDate + ", userId=" + userId + ", dueDate=" + dueDate
				+ ", location=" + location + ", viewCnt=" + viewCnt + ", category=" + category + ", teamContent="
				+ teamContent + "]";
	}


}
