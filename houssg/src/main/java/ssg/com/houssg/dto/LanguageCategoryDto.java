package ssg.com.houssg.dto;

public class LanguageCategoryDto {

	private int languageId;
	private String languageName;

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
	}

	public String getLanguageName() {
		return languageName;
	}

	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}

	@Override
	public String toString() {
		return "LanguageCategoryDto [languageId=" + languageId + ", languageName=" + languageName + "]";
	}

}
