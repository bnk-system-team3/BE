package ssg.com.houssg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ssg.com.houssg.dao.BoardDao;
import ssg.com.houssg.dto.BoardDto;

@Service
@Transactional
public class BoardService {
	
	@Autowired
	BoardDao dao;
	
	public void saveBoard(BoardDto board) {
		dao.saveBoard(board);
	}
}
