package ssg.com.houssg.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import ssg.com.houssg.dto.BoardDto;
import ssg.com.houssg.dto.ParticipantBoardDto;
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

	// 모임 참가 신청
	void applyForParticipation(ParticipantBoardDto participantDto);

	// 참가자 상태 업데이트
	void updateParticipantStatus(ParticipantBoardDto participantBoardDto);

	// 작성자가 생성한 프로젝트 목록 조회
	List<BoardDto> getMyProjects(String userId);

	// 해당 프로젝트에 지원한 참가자 이름 및 가입신청 상태 조회
	List<ParticipantBoardDto> getApplicantsForProject(int boardId);

	// 카테고리별 게시글 조회
	List<BoardDto> getBoardByCategory(String category);

	// 키워드별 게시글 조회
	List<BoardDto> getBoardByKeyword(String keyword);

	// viewCnt가 가장 높은 순서로 게시글 조회
	List<BoardDto> getBoardByViewCnt();
	
	// 팀컨텐츠 작성 및 수정
	void updateTeamContent(BoardDto boardDto);
	
	// 기술 스택 삽입 메서드
    void insertTechStack(Map<String, Object> params);

    // 언어 ID 조회 메서드
    Integer selectLanguageId(String tech);
    
    // 포지션 삽입 메서드
    void insertNeedPosition(Map<String, Object> params);

    // 포지션 ID 조회 메서드
    Integer selectNeedPosition(String position);
    
    int findBoardId(String userId);
    
    // 게시글 상세 정보 조회
    BoardDto getBoardDetails(int boardId);

    // 기술 스택 정보 조회
    List<String> getTechStack(int boardId);

    // 포지션 정보 조회
    List<String> getPositions(int boardId);
    
    // 조회수 증가
    void incrementViewCount(int boardId);


}
