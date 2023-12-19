package ssg.com.houssg.dto;

import java.util.Date;

public class ReviewDto {
	
	private int reviewId;
	private String reviewContent;
	private int boardId;
	private String userId;
	private Date createDate;
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getReviewContent() {
		return reviewContent;
	}
	public void setReviewContent(String reviewContent) {
		this.reviewContent = reviewContent;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Override
	public String toString() {
		return "ReviewDto [reviewId=" + reviewId + ", reviewContent=" + reviewContent + ", boardId=" + boardId
				+ ", userId=" + userId + ", createDate=" + createDate + "]";
	}
	
	
}
