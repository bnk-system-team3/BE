package ssg.com.houssg.dto;

public class ParticipantBoardDto {
	
	private int boardId;
	private String userId;
	private int captainFlag; // 모임장(1), 팀원(0)
	private int joinFlag; // 1(참가), 2(승인대기), 3(거절)
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCaptainFlag() {
		return captainFlag;
	}
	public void setCaptainFlag(int captinFlag) {
		this.captainFlag = captinFlag;
	}
	public int getJoinFlag() {
		return joinFlag;
	}
	public void setJoinFlag(int joinFlag) {
		this.joinFlag = joinFlag;
	}
	@Override
	public String toString() {
		return "ParticipantBoardDto [boardId=" + boardId + ", userId=" + userId + ", captainFlag=" + captainFlag
				+ ", joinFlag=" + joinFlag + "]";
	}
	
	
}
