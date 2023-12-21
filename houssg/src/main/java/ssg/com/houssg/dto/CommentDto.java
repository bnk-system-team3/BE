package ssg.com.houssg.dto;

import java.util.Date;

public class CommentDto {
	
	private int boardId;
	private String userId;
	private int cmtId;
	private String cmt;
	private Date createDate;
	
	
	
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
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
	public int getCmtId() {
		return cmtId;
	}
	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	@Override
	public String toString() {
		return "CommentDto [boardId=" + boardId + ", userId=" + userId + ", cmtId=" + cmtId + ", cmt=" + cmt
				+ ", createDate=" + createDate + "]";
	}
	
	
	
	
}	
