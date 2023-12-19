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
}
