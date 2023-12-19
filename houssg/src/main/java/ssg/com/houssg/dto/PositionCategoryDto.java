package ssg.com.houssg.dto;

public class PositionCategoryDto {
	private int positionId;
	private String positionName;

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Override
	public String toString() {
		return "PositionCategoryDto [positionId=" + positionId + ", positionName=" + positionName + "]";
	}
}
