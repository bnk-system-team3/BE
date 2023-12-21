package ssg.com.houssg.service;

import java.util.List;

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
}
