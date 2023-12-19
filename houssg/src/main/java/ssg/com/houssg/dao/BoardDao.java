package ssg.com.houssg.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.houssg.dto.BoardDto;
import ssg.com.houssg.dto.ReviewDto;

@Mapper
@Repository
public interface BoardDao {

	// 게시글 저장
	void saveBoard(BoardDto board);
	
	// 게시글 수정
	void updateBoard(BoardDto board);
	
	// 유저id로 게시글 조회
	String findUser(BoardDto board);
	
	// 게시글 요약본 조회(홈화면 정보 추출)
	List<BoardDto> getHomeBoardList();
	
	// 리뷰 작성
	void saveReview(ReviewDto review);
}
