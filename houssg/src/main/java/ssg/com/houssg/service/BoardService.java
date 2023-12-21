package ssg.com.houssg.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.houssg.dao.BoardDao;
import ssg.com.houssg.dto.BoardDto;
import ssg.com.houssg.dto.ParticipantBoardDto;
import ssg.com.houssg.dto.ReviewDto;

@Service
@Transactional
public class BoardService {

	@Autowired
	BoardDao dao;

	public void saveBoard(BoardDto board) {
		dao.saveBoard(board);
	}

	public void updateBoard(BoardDto board) {
		dao.updateBoard(board);
	}

	public String findUser(BoardDto board) {
		return dao.findUser(board);
	}

	public List<BoardDto> getHomeBoardList() {
		return dao.getHomeBoardList();
	}

	public void saveReview(ReviewDto review) {
		dao.saveReview(review);
	}

	public void applyForParticipation(ParticipantBoardDto participantDto) {
		dao.applyForParticipation(participantDto);
	}

	public void updateParticipantStatus(int boardId, int joinFlag, String userId) {
		ParticipantBoardDto participantBoardDto = new ParticipantBoardDto();
		participantBoardDto.setBoardId(boardId);
		participantBoardDto.setJoinFlag(joinFlag);
		participantBoardDto.setUserId(userId);

		dao.updateParticipantStatus(participantBoardDto);
	}

	public List<BoardDto> getMyProjects(String userId) {
		return dao.getMyProjects(userId);
	}

	public List<ParticipantBoardDto> getApplicantsForProject(int boardId) {
		return dao.getApplicantsForProject(boardId);
	}

	public List<BoardDto> getBoardByCategory(String category) {
		return dao.getBoardByCategory(category);
	}

	public List<BoardDto> getBoardByKeyword(String keyword) {
		return dao.getBoardByKeyword(keyword);
	}

	public List<BoardDto> getBoardByViewCnt() {
		return dao.getBoardByViewCnt();
	}

	public void updateTeamContent(BoardDto boardDto) {
		dao.updateTeamContent(boardDto);
	}

	// 기술 스택 삽입 메서드
	public void insertTechStack(int boardId, String tech) {
		// 언어 ID 조회
		Integer languageId = dao.selectLanguageId(tech);

		// 맵에 파라미터 설정
		Map<String, Object> params = Map.of("boardId", boardId, "languageId", languageId);

		// 기술 스택 삽입
		dao.insertTechStack(params);
	}

	// 포지션 삽입 메서드
	public void insertNeedPosition(int boardId, String position) {
		// 포지션 ID 조회
		Integer positionId = dao.selectNeedPosition(position);

		// 맵에 파라미터 설정
		Map<String, Object> params = Map.of("boardId", boardId, "positionId", positionId);

		// 포지션 삽입
		dao.insertNeedPosition(params);
	}

	public int findBoardId(String userId) {
		return dao.findBoardId(userId);
	}

	public Map<String, Object> getBoardDetails(int boardId) {
		// 게시글 상세 정보 조회
		BoardDto boardDetails = dao.getBoardDetails(boardId);

		if (boardDetails != null) {
			// 기술 스택 및 포지션 정보 조회
			List<String> techStack = dao.getTechStack(boardId);
			List<String> positions = dao.getPositions(boardId);

			// 결과를 Map으로 구성
			Map<String, Object> result = new HashMap<>();
			result.put("boardDetails", boardDetails);
			result.put("techStack", techStack);
			result.put("positions", positions);

			return result;
		} else {
			return null;
		}
	}
	
	public void incrementViewCount(int boardId) {
	    dao.incrementViewCount(boardId);
	}

}
