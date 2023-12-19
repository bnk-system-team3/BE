package ssg.com.houssg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.houssg.dto.LanguageCategoryDto;
import ssg.com.houssg.dto.PositionCategoryDto;

@Mapper
@Repository
public interface InterestDao {
	// 관심 언어
	List<LanguageCategoryDto> getLanguages();
	
	// 관심 분야
	List<PositionCategoryDto> getPositions();
	
	// 관심 언어 저장
	void saveComment(LanguageCategoryDto interest);

	// 관심 분야 저장
	void saveComment(PositionCategoryDto interest);
	
	// 관심 언어 변경
	void updateComment(LanguageCategoryDto interest);

	// 관심 분야 변경
	void updateComment(PositionCategoryDto interest);
}
